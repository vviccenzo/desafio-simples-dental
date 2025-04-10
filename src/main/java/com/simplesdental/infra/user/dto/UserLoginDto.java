package com.simplesdental.infra.user.dto;

import jakarta.validation.constraints.NotBlank;

public class UserLoginDto {

    @NotBlank(message = "Name is required")
    private String email;

    @NotBlank(message = "Password is required")
    private String password;

    public UserLoginDto(@NotBlank(message = "Name is required") String email,
            @NotBlank(message = "Password is required") String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
