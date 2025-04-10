package com.simplesdental.application.user.usecases;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.simplesdental.application.user.gateways.UserContextGateway;
import com.simplesdental.application.user.gateways.UserRepositoryGateway;
import com.simplesdental.domain.user.entities.User;
import com.simplesdental.infra.user.dto.UserContext;

public class UserContextUseCase implements UserContextGateway {

    private static final Logger logger = LoggerFactory.getLogger(UserContextUseCase.class);

    private final UserRepositoryGateway userRepositoryGateway;

    public UserContextUseCase(UserRepositoryGateway userRepositoryGateway) {
        this.userRepositoryGateway = userRepositoryGateway;
    }

    @Override
    // @Cacheable(value = "userContextCache", key = "#userId")
    public UserContext execute(String email) {
        logger.info("Executando a consulta para o usuário {}", email);
        User user = this.userRepositoryGateway.findByEmail(email);
        if (user == null) {
            return null; 
        }

        UserContext context = new UserContext();
        context.setId(user.getId());
        context.setEmail(user.getEmail());
        context.setRole(user.getRole());

        return context;
    }

}
