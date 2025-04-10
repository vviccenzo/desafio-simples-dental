package com.simplesdental.infra.user.persistence;

import java.util.Objects;

import org.springframework.stereotype.Repository;

import com.simplesdental.application.user.gateways.UserRepositoryGateway;
import com.simplesdental.domain.user.entities.User;
import com.simplesdental.domain.user.mapper.UserMapper;

import jakarta.persistence.EntityNotFoundException;

@Repository
public class UserRepositoryJPA implements UserRepositoryGateway {

    private UserRepository userRepository;

    public UserRepositoryJPA(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findByEmail(String email) {
        UserEntity userEntity = this.userRepository.findByEmail(email);
        if (Objects.isNull(userEntity)) {
            throw new EntityNotFoundException("User not found");
        }

        return UserMapper.toUser(userEntity);
    }

    @Override
    public User save(User user) {
        UserEntity userEntity = UserMapper.toUserEntity(user);
        userEntity = this.userRepository.save(userEntity);
        return UserMapper.toUser(userEntity);
    }

    @Override
    public boolean existsByEmail(String email) {
        return this.userRepository.existsByEmail(email);
    }

}
