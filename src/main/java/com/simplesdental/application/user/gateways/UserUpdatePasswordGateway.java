package com.simplesdental.application.user.gateways;

import com.simplesdental.infra.user.dto.UserUpdatePasswordDto;

public interface UserUpdatePasswordGateway {

    public void execute(UserUpdatePasswordDto userUpdatePasswordDto);

}
