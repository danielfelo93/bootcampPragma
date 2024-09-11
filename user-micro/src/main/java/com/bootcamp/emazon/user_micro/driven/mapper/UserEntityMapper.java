package com.bootcamp.emazonapi.driven.mapper;

import com.bootcamp.emazonapi.domain.service.User;
import com.bootcamp.emazonapi.driven.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;


@Mapper(componentModel = "spring")
public interface UserEntityMapper {

    UserEntity userToUserEntity(User user);
    User entityToUser(UserEntity userEntity);
    List<User> userToUserEntityList(List<UserEntity> userEntities);
}
