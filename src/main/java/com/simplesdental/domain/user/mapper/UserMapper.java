package com.simplesdental.domain.user.mapper;

import java.util.ArrayList;
import java.util.List;

import com.simplesdental.domain.category.entities.Category;
import com.simplesdental.domain.category.mapper.CategoryMapper;
import com.simplesdental.domain.user.entities.User;
import com.simplesdental.infra.user.dto.UserContext;
import com.simplesdental.infra.user.dto.UserLoginDto;
import com.simplesdental.infra.user.dto.UserRegisterDto;
import com.simplesdental.infra.user.dto.UserUpdatePasswordDto;
import com.simplesdental.infra.user.persistence.UserEntity;
import com.simplesdental.infra.user.persistence.UserRole;

public class UserMapper {

    public static UserRegisterDto toUserRegisterDto(String name, String email, String password) {
        return new UserRegisterDto(name, email, password);
    }

    public static UserLoginDto toUserLoginDto(String email, String password) {
        return new UserLoginDto(email, password);
    }

    public static UserUpdatePasswordDto toUserUpdatePasswordDto(String currentPassword, String newPassword, Long userId) {
        return new UserUpdatePasswordDto(currentPassword, newPassword, userId);
    }

    public static UserContext toUserContext(String email, String name, UserRole role, String token) {
        return new UserContext(email, name, role, token);
    }

    public static UserEntity toUserEntity(User user) {
        return new UserEntity(user.getName(), user.getEmail(), user.getPassword(), UserRole.USER, new ArrayList<>());
    }

    public static User toUser(UserRegisterDto userRegisterDto) {
        User user = new User();
        user.setName(userRegisterDto.getName());
        user.setEmail(userRegisterDto.getEmail());
        user.setPassword(userRegisterDto.getPassword());
        user.setRole(UserRole.USER);
        user.setCategories(new ArrayList<>());

        return user;
    }

    public static User toUser(UserEntity userEntity) {
        User user = new User();
        user.setId(userEntity.getId());
        user.setName(userEntity.getName());
        user.setEmail(userEntity.getEmail());
        user.setPassword(userEntity.getPassword());
        user.setRole(userEntity.getRole());

        List<Category> categories = userEntity.getCategories().stream().map(CategoryMapper::toCategory).toList();
        user.setCategories(categories);

        return user;
    }

}
