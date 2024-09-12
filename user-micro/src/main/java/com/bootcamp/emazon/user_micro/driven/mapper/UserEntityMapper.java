package com.bootcamp.emazon.user_micro.driven.mapper;

import com.bootcamp.emazon.user_micro.domain.service.User;
import com.bootcamp.emazon.user_micro.driven.entity.UserEntity;
import org.mapstruct.Mapper;


import java.util.List;


@Mapper(componentModel = "spring")
public interface UserEntityMapper {

    UserEntity userToUserEntity(User user);
    User entityToUser(UserEntity userEntity);
    List<User> userToUserEntityList(List<UserEntity> userEntities);
}
