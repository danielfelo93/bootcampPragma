package com.bootcamp.emazon.user_micro.driven.adapter;

import com.bootcamp.emazon.user_micro.domain.service.User;
import com.bootcamp.emazon.user_micro.domain.spi.IUserPersistencePort;
import com.bootcamp.emazon.user_micro.driven.entity.UserEntity;
import com.bootcamp.emazon.user_micro.driven.exceptions.NoDataFoundException;
import com.bootcamp.emazon.user_micro.driven.mapper.UserEntityMapper;
import com.bootcamp.emazon.user_micro.driven.repository.IUserRepository;
import com.bootcamp.emazon.user_micro.driving.dto.response.PagedResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class UserAdaptador implements IUserPersistencePort {

    private final IUserRepository userRepository;
    private final UserEntityMapper userEntityMapper;


    @Override
    public User guardarUsuario(User user) {
        return userEntityMapper.entityToUser(userRepository.save(userEntityMapper.userToUserEntity(user)));
    }

    @Override
    public Optional<User> encontrarPorCorreo(String correo) {
        return userRepository.findByCorreo(correo)
                .map(userEntityMapper::entityToUser);
    }

    @Override
    public boolean existsByCorreo(String correo) {
        return userRepository.existsByCorreo(correo);
    }

    @Override
    public PagedResponse<User> listarUsuarios(int page, int size, String order) {
        Pageable pageable;

        if ("desc".equalsIgnoreCase(order)) {
            pageable = PageRequest.of(page, size, Sort.by("nombre").descending());
        } else if ("asc".equalsIgnoreCase(order)) {
            pageable = PageRequest.of(page, size, Sort.by("nombre").ascending());
        } else {
            pageable = PageRequest.of(page, size, Sort.by("id"));
        }

        Page<UserEntity> userPage = userRepository.findAll(pageable);
        List<UserEntity> users = userPage.getContent();

        if (users.isEmpty()) {
            throw new NoDataFoundException();
        }

        List<User> userList = userEntityMapper.userToUserEntityList(users);

        return new PagedResponse<>(
                userList,
                userPage.getNumber(),
                userPage.getSize(),
                userPage.getTotalElements(),
                userPage.getTotalPages(),
                userPage.isLast()
        );
    }
}
