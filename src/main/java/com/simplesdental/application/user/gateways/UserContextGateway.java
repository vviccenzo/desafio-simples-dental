package com.simplesdental.application.user.gateways;

import com.simplesdental.infra.user.dto.UserContext;

public interface UserContextGateway {
    
    public UserContext execute(String email);

}
