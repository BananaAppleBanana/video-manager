package com.antra.videomanager.configure;

import com.antra.videomanager.security.JwtAuthenticationEntryPoint;
import com.antra.videomanager.security.filter.JwtAuthorizationTokenFilter;
import com.antra.videomanager.security.filter.JsonUsernamePasswordAuthenticationFilter;
import com.antra.videomanager.security.pojo.JwtUser;
import com.antra.videomanager.security.utility.JwtTokenUtil;
import com.antra.videomanager.security.utility.SHAEncoder;
import com.antra.videomanager.utils.GsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Value("${jwt.header}")
    private String tokenHeader;

    private final JwtTokenUtil jwtTokenUtil;

    private final UserDetailsService jwtUserDetailsService;

    private final JwtAuthenticationEntryPoint jwtUnauthorizedHandler;

    @Autowired
    public SecurityConfig(JwtTokenUtil jwtTokenUtil, UserDetailsService jwtUserDetailsService, JwtAuthenticationEntryPoint jwtUnauthorizedHandler) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.jwtUserDetailsService = jwtUserDetailsService;
        this.jwtUnauthorizedHandler = jwtUnauthorizedHandler;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(jwtUserDetailsService)
                .passwordEncoder(passwordEncoderBean());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public JsonUsernamePasswordAuthenticationFilter jsonUsernamePasswordAuthenticationFilter() throws Exception {
        JsonUsernamePasswordAuthenticationFilter jwtFilter = new JsonUsernamePasswordAuthenticationFilter();
        jwtFilter.setAuthenticationManager(authenticationManagerBean());
        jwtFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler());
        jwtFilter.setAuthenticationFailureHandler(authenticationFailureHandler());
        return jwtFilter;
    }

    @Bean
    public PasswordEncoder passwordEncoderBean() {
        return new SHAEncoder();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .cors().and()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling().authenticationEntryPoint(jwtUnauthorizedHandler)
                .and()
                .authorizeRequests()
                .antMatchers("/api/user/login").permitAll()
                .anyRequest().authenticated();
//                .and()
//                .formLogin().loginProcessingUrl("/api/user/login");

        JwtAuthorizationTokenFilter authorizationTokenFilter = new JwtAuthorizationTokenFilter(userDetailsService(), jwtTokenUtil, tokenHeader);

        httpSecurity
                .addFilterAt(jsonUsernamePasswordAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(authorizationTokenFilter, JsonUsernamePasswordAuthenticationFilter.class);

        httpSecurity
                .headers()
                .frameOptions().sameOrigin()
                .cacheControl();
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return (httpServletRequest, httpServletResponse, authentication) -> {
            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
            PrintWriter writer = httpServletResponse.getWriter();
            final Map<String, String> responseToken = new HashMap<>();
            responseToken.put("loginResult", "success");
            responseToken.put("bearerToken", "Bearer " + jwtTokenUtil.generateToken((JwtUser)authentication.getPrincipal()));
            writer.write(GsonUtil.getObj().getGson().toJson(responseToken));
            writer.flush();
        };
    }

    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return (httpServletRequest, httpServletResponse, e) -> {
            httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
            PrintWriter writer = httpServletResponse.getWriter();
            final Map<String, String> responseToken = new HashMap<>();

            responseToken.put("loginResult", "Fail");
            responseToken.put("error", "Incorrect username or password. ");
            writer.write(GsonUtil.getObj().getGson().toJson(responseToken));
            writer.flush();
        };
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }
}