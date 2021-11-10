package com.antra.videomanager.repository.impl;

import com.antra.videomanager.repository.VideoLibraryRepositoryCustom;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class VideoLibraryRepositoryCustomImpl implements VideoLibraryRepositoryCustom{

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Object[]> countLibraryTypeForUser(String userId) {
        final String qlString = "SELECT l.libraryType, COUNT(u.userId) FROM LibraryType_L l JOIN l.userVideoLibrarySet vl " +
                " JOIN vl.user u WHERE u.userId = :userId GROUP BY l.libraryType ";
        Query q = em.createQuery(qlString)
                    .setParameter("userId", userId);
        return q.getResultList();
    }
}

/**
 * how to secure web service
 *  [1.] authentication : 401
 *  [2.] authorization  : 403
 *
 *  OAuth2.0
 *      1. login in : username password
 *      2. generate token ( JWT token )
 *      3. send token back to user
 *      4. next request (header : token)
 *      5. verify token
 *
 *  Spring security
 *      Authentication Manager
 *      1. impl User Details Service -> load user info(User Details)
 *      2. impl User Details
 *      3. create customized filter to replace UsernamePasswordAuthenticationFilter
 *      4. connect user details service with authentication manager
 *      5. add customized filter into spring security filter chain
 *      6. authenticate in DAOAuthenticationProvider
 *      7. generate JWT token -> return user
 *         JWT Token : encode(header.payload.signature(encryption(header + payload)))
 *         encode -> decode
 *         encrypt -> decrypt (need key)
 *      8. client send JWT token with header
 *      9. create JWTFilter -> add it into spring security filter chain
 *         verify header JWT token
 *     10. add verified user authentication into security context(Thread local)
 *     11. @PreAuthorize("hasRole[...]")
 *
 *  HTTP -> non-secure
 *  1. encrypt body
 *  2. HTTPS
 *
 *  [3.] HTTP + SSL / TLS = HTTPS  -> 1 way SSL / 2 way SSL
 *  1. certificate
 *  2. client -> send hi -> server
 *  3. server -> send certificate + public key -> client
 *      public key encrypt -> private decrypt
 *      private key encrypt -> public decrypt
 *  4. key exchange (generate symmetric key)
 *  5. client -> encrypt[random string] -> server
 *  6. compare string response from server
 *  7. generate symmetric key
 *
 *
 *  [4.] password -> String(CSP -> heap dump) / char[]
 *  [5.] database encryption / file encryption / hashing
 *       S3 -> client encryption
 *          -> S3-encryption
 *          -> KMS key management system
 *  [6.] CORS -> cross origin access
 *       www.myWeb.com
 *       www.abc123.com -> redirect www.myWeb.com
 *       preFlight
 *       3rd party origin -> preFlight -> origin address
 *       3rd party origin -> header    -> origin address
 *  ddos ->
 *  CSRF -> pic -> www.myWeb.com?transferFrom=..&transferTo=..
 *       -> html hidden token
 *
 *  SQLInjection
 *
 *
 *  jwt.io
 *      jwt token ->
 *
 *  1. Spring boot advantage
 *  2. Microservice
 *  3. Message Queue
 *  4. AWS -> service concept
 *            S3
 *            ASG / EC2
 *            ECS / RCR
 *            VPC (subnet)
 *  5. Test / Agile scrum
 *  6. Jira, Jenkins
 *
 *
 *  WHITEBOARD
 *  restapi design + white board + annotations
 *
*/