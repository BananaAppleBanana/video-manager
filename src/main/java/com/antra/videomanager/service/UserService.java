package com.antra.videomanager.service;

import com.antra.videomanager.domain.Response;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    Response findUserInfoByUserId(String userId);

    List<Object> findAllUserInfo();
}
