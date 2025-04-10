package com.simplesdental.infra.user.dto;

import jakarta.validation.constraints.NotBlank;

public class UserUpdatePasswordDto {

    private Long userId;

    @NotBlank(message = "Actual password is required")
    private String actualPassword;

    @NotBlank(message = "New password is required")
    private String newPassword;

    public UserUpdatePasswordDto(String actualPassword, String newPassword, Long userId) {
        this.actualPassword = actualPassword;
        this.newPassword = newPassword;
        this.userId = userId;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

}
