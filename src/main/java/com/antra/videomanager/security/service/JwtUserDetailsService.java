package com.antra.videomanager.security.service;

import com.antra.videomanager.domain.entity.User;
import com.antra.videomanager.repository.UserRepository;
import com.antra.videomanager.security.factory.JwtUserFactory;
import com.antra.videomanager.security.pojo.JwtUser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService{

    private final UserRepository userRepository;

    private final Logger logger = LogManager.getLogger(this.getClass());

    @Autowired
    public JwtUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUserName(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        } else {
            JwtUser jwtUser = JwtUserFactory.create(user);
            return jwtUser;
        }
    }

//    private User mapper(List<UserRole> userRoles, String userName) throws UsernameNotFoundException {
//        if(userRoles == null || userRoles.size() == 0) {
//            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", userName));
//        }
//        return userRoles.stream()
//                .collect(User::new, (user, userRole) -> {
//                    if(user.getUserRoleSet() == null) {
//                        user.setUserRoleSet(new HashSet<>());
//                    }
//                    user.getUserRoleSet().add(userRole);
//                }, (u1, u2) -> u1.getUserRoleSet().addAll(u2.getUserRoleSet()));
//    }
}
