package com.antra.videomanager.domain.mapper;

import com.antra.videomanager.domain.entity.User;
import com.antra.videomanager.domain.response.UserInfoResponse;
import com.antra.videomanager.tool.mapper.Attribute;
import com.antra.videomanager.tool.mapper.Mapper;
import com.antra.videomanager.tool.mapper.Mapping;

@Mapper
public interface UserInfoMapper {

    @Mapping(target = UserInfoResponse.class,
             value = {
                @Attribute(from = "roleId", to = "roles")
             })
    Object convertUserToUserInfoResponse(User user);
}
