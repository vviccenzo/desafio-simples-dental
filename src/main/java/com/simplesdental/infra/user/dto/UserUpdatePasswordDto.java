package com.simplesdental.infra.user.dto;

import jakarta.validation.constraints.NotBlank;

public class UserUpdatePasswordDto {

    private String email;

    @NotBlank(message = "Actual password is required")
    private String actualPassword;

    @NotBlank(message = "New password is required")
    private String newPassword;

    public UserUpdatePasswordDto() {
        
    }

    public UserUpdatePasswordDto(String actualPassword, String newPassword) {
        this.actualPassword = actualPassword;
        this.newPassword = newPassword;
    }

    public String getActualPassword() {
        return actualPassword;
    }

    public void setActualPassword(String actualPassword) {
        this.actualPassword = actualPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
