package com.antra.videomanager;

import com.antra.videomanager.tool.mapper.MapperScan;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;

@SpringBootApplication
@MapperScan(
		value = {"com.antra.videomanager.domain"}
)
@EnableAsync
public class VideoManagerApplication {
//	public static void main(String[] args) {
//		SpringApplicationBuilder builder = new SpringApplicationBuilder(VideoManagerApplication.class);
//		builder.headless(false).run(args);
//	}
}
/**
	1. authentication -> user / pwd / identity -> 401
	2. authorization -> role (jwt) -> 403
	3. https -> ssl / tls
	4. encryption db -> client encryption / s3 encrypt / kms
	5. hash sensitive data -> string(immutable) / char[] -> dump file
	6. sql injection -> ;\"select * from.."  ,  or true
	7. XSS / CRSF (CORS)(token, <hidden></hidden>)
	8. DDOS

    CORS  -> A, B
 		 A -> pre flight ->  B
 			<-access origin
    	 A ->



 	user -> login -> server -> sessionid

 		 loadbalancer(sticky session)

 	s1		s2		s3

    OAuth2.0
 	user -> login -> server -> generate token(JWT) -> user
 	user -> token in header(Authorization : bearer + token) -> resource api
    JWT(header.payload.signature)
    encode(header.payload.encrypt(header.payload))
    encode => is not safe => decode
    encrypt => key => decrypt

    https = http + ssl / tls (certificate)
    www.mygoogle.com => login info
    www.google.com
 	clients 				      			server(private key)
 			        -> hello
           <- hi(public key + certificate)
                    -> random string
					<- hash string
 			-> public key[symmetric key[data]]
 -> 		   private key[symmetric key[response]]

 	Spring security
 	1. write user details + user details service + provider
    2. add impls into authentication manager
 	3. UsernamePasswordAuthenticationFilter -> read user details from form data
 	4. authenticate() => DaoAuthentication Provider
 	5. dao provider retrieves user details from db / cache
 	6. compare user info from client and from db / cache
 	7. store user details in securityContext(ThreadLocal)




	DispatcherServlet(/*) => controller
		|
	http message converter (jackson)
 */

