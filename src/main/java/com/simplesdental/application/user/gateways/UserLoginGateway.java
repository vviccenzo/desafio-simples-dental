package com.simplesdental.application.user.gateways;

import com.simplesdental.infra.user.dto.UserLoginDto;

public interface UserLoginGateway {
    
    public void execute(UserLoginDto userLoginDto);

}
