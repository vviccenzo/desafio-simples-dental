package com.simplesdental.application.user.usecases;

import com.simplesdental.application.user.gateways.UserRegisterGateway;
import com.simplesdental.application.user.gateways.UserRepositoryGateway;
import com.simplesdental.domain.user.entities.User;
import com.simplesdental.domain.user.mapper.UserMapper;
import com.simplesdental.infra.user.dto.UserRegisterDto;
import com.simplesdental.security.PasswordEncoderUtil;

public class UserRegisterUseCase implements UserRegisterGateway {

    private UserRepositoryGateway userRepositoryGateway;

    public UserRegisterUseCase(UserRepositoryGateway userRepositoryGateway) {
        this.userRepositoryGateway = userRepositoryGateway;
    }

    public void execute(UserRegisterDto userRegisterDto) {
        if (this.userRepositoryGateway.existsByEmail(userRegisterDto.getEmail())) {
            throw new RuntimeException("User already exists");
        }

        User user = UserMapper.toUser(userRegisterDto);
        String encodedPassword = PasswordEncoderUtil.encode(user.getPassword());
        user.setPassword(encodedPassword);

        this.userRepositoryGateway.save(user);
    }

}
