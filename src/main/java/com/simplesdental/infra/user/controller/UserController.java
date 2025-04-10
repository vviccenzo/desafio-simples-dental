package com.simplesdental.infra.user.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.simplesdental.application.user.gateways.UserContextGateway;
import com.simplesdental.application.user.gateways.UserLoginGateway;
import com.simplesdental.application.user.gateways.UserRegisterGateway;
import com.simplesdental.application.user.gateways.UserUpdatePasswordGateway;
import com.simplesdental.infra.user.dto.UserContext;
import com.simplesdental.infra.user.dto.UserLoginDto;
import com.simplesdental.infra.user.dto.UserRegisterDto;
import com.simplesdental.infra.user.dto.UserUpdatePasswordDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/user")
@Tag(name = "Usuários", description = "Endpoints relacionados a operações de autenticação e gerenciamento de usuários")
public class UserController {

    private final UserRegisterGateway userRegisterGateway;
    private final UserLoginGateway userLoginGateway;
    private final UserContextGateway userContextGateway;
    private final UserUpdatePasswordGateway userUpdatePasswordGateway;

    public UserController(UserRegisterGateway userRegisterGateway, UserLoginGateway userLoginGateway,
            UserContextGateway userContextGateway, UserUpdatePasswordGateway userUpdatePasswordGateway) {
        this.userRegisterGateway = userRegisterGateway;
        this.userLoginGateway = userLoginGateway;
        this.userContextGateway = userContextGateway;
        this.userUpdatePasswordGateway = userUpdatePasswordGateway;
    }

    @PostMapping("/auth/login")
    @Operation(summary = "Realiza login", description = "Autentica um usuário com base nas credenciais fornecidas.", requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, description = "Credenciais de login", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserLoginDto.class), examples = @ExampleObject(name = "Exemplo de Login", value = "{ \"email\": \"usuario@exemplo.com\", \"senha\": \"senha123\" }"))), responses = {
            @ApiResponse(responseCode = "200", description = "Login bem-sucedido"),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
    })
    public ResponseEntity<Void> login(@Valid @RequestBody UserLoginDto request) {
        this.userLoginGateway.execute(request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/auth/register")
    @Operation(summary = "Registra novos usuários", description = "Registra um novo usuário no sistema.", requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, description = "Dados do usuário para registro", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserRegisterDto.class), examples = @ExampleObject(name = "Exemplo de Registro", value = "{ \"nome\": \"Usuário Exemplo\", \"email\": \"usuario@exemplo.com\", \"senha\": \"senha123\" }"))), responses = {
            @ApiResponse(responseCode = "201", description = "Usuário registrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    })
    public ResponseEntity<Void> register(@Valid @RequestBody UserRegisterDto request) {
        this.userRegisterGateway.execute(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/auth/context")
    @Operation(summary = "Retorna o contexto do usuário autenticado", description = "Fornece informações sobre o usuário atualmente autenticado.", responses = {
            @ApiResponse(responseCode = "200", description = "Informações do usuário autenticado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserContext.class))),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado")
    })
    public ResponseEntity<UserContext> getUserContext() {
        UserContext context = this.userContextGateway.execute();
        return ResponseEntity.ok(context);
    }

    @PutMapping("/users/password")
    @Operation(summary = "Atualiza a senha do usuário autenticado", description = "Permite que o usuário autenticado atualize sua senha.", requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, description = "Dados para atualização de senha", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserUpdatePasswordDto.class), examples = @ExampleObject(name = "Exemplo de Atualização de Senha", value = "{ \"senhaAtual\": \"senha123\", \"novaSenha\": \"novaSenha123\" }"))), responses = {
            @ApiResponse(responseCode = "204", description = "Senha atualizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado")
    })
    public ResponseEntity<Void> updatePassword(@Valid @RequestBody UserUpdatePasswordDto request) {
        this.userUpdatePasswordGateway.execute(request);
        return ResponseEntity.noContent().build();
    }
}
