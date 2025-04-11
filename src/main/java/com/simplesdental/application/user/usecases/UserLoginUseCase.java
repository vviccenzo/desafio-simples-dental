package com.simplesdental.application.user.usecases;

import com.simplesdental.application.user.gateways.UserLoginGateway;
import com.simplesdental.application.user.gateways.UserRepositoryGateway;
import com.simplesdental.domain.user.entities.User;
import com.simplesdental.infra.security.PasswordEncoderUtil;
import com.simplesdental.infra.security.TokenProvider;
import com.simplesdental.infra.user.dto.UserContext;
import com.simplesdental.infra.user.dto.UserLoginDto;

public class UserLoginUseCase implements UserLoginGateway {

    private UserRepositoryGateway userRepositoryGateway;

    public UserLoginUseCase(UserRepositoryGateway userRepositoryGateway) {
        this.userRepositoryGateway = userRepositoryGateway;
    }

    public UserContext execute(UserLoginDto userLoginDto) {
        User user = this.userRepositoryGateway.findByEmail(userLoginDto.getEmail());
        if (user == null) {
            throw new RuntimeException("Usuário não encontrado");
        }

        boolean isPasswordValid = PasswordEncoderUtil.matches(userLoginDto.getPassword(), user.getPassword());
        if (!isPasswordValid) {
            throw new RuntimeException("Senha inválida");
        }

        String token = TokenProvider.generateToken(user.getEmail(), user.getRole().toString());
        UserContext userContext = new UserContext(user.getName(), user.getEmail(), user.getRole(), token);
        return userContext;
    }

}
