package com.simplesdental.application.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.simplesdental.application.user.gateways.UserContextGateway;
import com.simplesdental.application.user.gateways.UserLoginGateway;
import com.simplesdental.application.user.gateways.UserRegisterGateway;
import com.simplesdental.application.user.gateways.UserRepositoryGateway;
import com.simplesdental.application.user.gateways.UserUpdatePasswordGateway;
import com.simplesdental.application.user.usecases.UserContextUseCase;
import com.simplesdental.application.user.usecases.UserLoginUseCase;
import com.simplesdental.application.user.usecases.UserRegisterUseCase;
import com.simplesdental.application.user.usecases.UserUpdatePasswordUseCase;

@Configuration
public class UserConfiguration {

    @Bean
    public UserRegisterGateway userRegisterGateway(UserRepositoryGateway userRepositoryGateway) {
        return new UserRegisterUseCase(userRepositoryGateway);
    }

    @Bean
    public UserLoginGateway userLoginGateway(UserRepositoryGateway userRepositoryGateway) {
        return new UserLoginUseCase(userRepositoryGateway);
    }

    @Bean
    public UserContextGateway userContextGateway(UserRepositoryGateway userRepositoryGateway) {
        return new UserContextUseCase(userRepositoryGateway);
    }

    @Bean
    public UserUpdatePasswordGateway userUpdatePasswordGateway(UserRepositoryGateway userRepositoryGateway) {
        return new UserUpdatePasswordUseCase();
    }
}
