package com.simplesdental.application.user.usecases;

import java.util.Objects;

import com.simplesdental.application.user.gateways.UserRepositoryGateway;
import com.simplesdental.application.user.gateways.UserUpdatePasswordGateway;
import com.simplesdental.domain.user.entities.User;
import com.simplesdental.infra.user.dto.UserUpdatePasswordDto;

public class UserUpdatePasswordUseCase implements UserUpdatePasswordGateway {

    private final UserRepositoryGateway userRepositoryGateway;

    public UserUpdatePasswordUseCase(UserRepositoryGateway userRepositoryGateway) {
        this.userRepositoryGateway = userRepositoryGateway;
    }

    public void execute(UserUpdatePasswordDto userUpdatePasswordDto) {
        User user = this.userRepositoryGateway.findByEmail(userUpdatePasswordDto.getEmail());
        if (Objects.nonNull(user)) {
            user.setPassword(userUpdatePasswordDto.getNewPassword());
            this.userRepositoryGateway.save(user);
        }
    }

}
