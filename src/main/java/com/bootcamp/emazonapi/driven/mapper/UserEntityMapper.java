package com.bootcamp.emazonapi.driven.mapper;

import com.bootcamp.emazonapi.domain.service.Marca;
import com.bootcamp.emazonapi.domain.service.User;
import com.bootcamp.emazonapi.driven.entity.MarcaEntity;
import com.bootcamp.emazonapi.driven.entity.UserEntity;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring")
public interface UserEntityMapper {

    User entityToUser(UserEntity userEntity);
    UserEntity userToUserEntity(User user);
    List<User> userToUserEntityList(List<UserEntity> userEntities);
}
