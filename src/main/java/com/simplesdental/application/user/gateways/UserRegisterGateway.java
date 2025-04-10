package com.simplesdental.application.user.gateways;

import com.simplesdental.infra.user.dto.UserRegisterDto;

public interface UserRegisterGateway {
    
    public void execute(UserRegisterDto userRegisterDto);

}
