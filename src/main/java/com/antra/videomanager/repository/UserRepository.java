package com.antra.videomanager.repository;

import com.antra.videomanager.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, String>{

    @Query("select u from User u " +
            "join fetch u.userRoleSet urs join fetch urs.role_l join fetch u.person where u.username =:username")
    User findUserByUserName(@Param("username") String username);


    @Query("select distinct u from User u " +
            "left join fetch u.person p " +
            "left join fetch p.company " +
            "left join fetch u.userRoleSet urs " +
            "where u.userId =:userId")
    User findUserByUserId(@Param("userId") String userId);

    @Query("select distinct u from User u " +
            "left join fetch u.person p " +
            "left join fetch p.company " +
            "left join fetch u.userRoleSet urs")
    List<User> findAllUser();


}
