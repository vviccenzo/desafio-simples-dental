package com.simplesdental.application.user.gateways;

import com.simplesdental.domain.user.entities.User;

public interface UserRepositoryGateway {

    public User findByEmail(String email);

    public User save(User user);

    public boolean existsByEmail(String email);

}
