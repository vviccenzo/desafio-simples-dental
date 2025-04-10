package com.simplesdental.application.user.gateways;

import com.simplesdental.infra.user.dto.UserContext;
import com.simplesdental.infra.user.dto.UserLoginDto;

public interface UserLoginGateway {
    
    public UserContext execute(UserLoginDto userLoginDto);

}
