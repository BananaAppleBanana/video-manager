package com.antra.videomanager.service.impl;

import com.antra.videomanager.domain.Response;
import com.antra.videomanager.domain.mapper.UserInfoMapper;
import com.antra.videomanager.domain.response.UserInfoResponse;
import com.antra.videomanager.domain.entity.User;
import com.antra.videomanager.repository.UserRepository;
import com.antra.videomanager.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    //private final UserMapper userMapper;
    private final UserInfoMapper userMapper;

    private final Logger logger = LogManager.getLogger(this.getClass());

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserInfoMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public Response findUserInfoByUserId(String userId) {
        User user = userRepository.findUserByUserId(userId);
        return (UserInfoResponse)userMapper.convertUserToUserInfoResponse(user);
    }

    @Override
    public List<Object> findAllUserInfo() throws IllegalArgumentException, NullPointerException {
        List<User> users = Optional.ofNullable(userRepository.findAllUser()).orElse(new ArrayList<>());
        List<Object> userInfoResponseList = new ArrayList<>();
        logger.debug("user response info {}", userInfoResponseList);
        for(User u: users) {
            userInfoResponseList.add(userMapper.convertUserToUserInfoResponse(u));
        }
        return userInfoResponseList;
    }


}
