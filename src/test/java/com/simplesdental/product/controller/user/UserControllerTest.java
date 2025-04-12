package com.simplesdental.product.controller.user;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.simplesdental.application.user.gateways.UserContextGateway;
import com.simplesdental.application.user.gateways.UserLoginGateway;
import com.simplesdental.application.user.gateways.UserRegisterGateway;
import com.simplesdental.application.user.gateways.UserUpdatePasswordGateway;
import com.simplesdental.infra.user.controller.UserController;
import com.simplesdental.infra.user.dto.UserContext;
import com.simplesdental.infra.user.dto.UserLoginDto;
import com.simplesdental.infra.user.dto.UserRegisterDto;
import com.simplesdental.infra.user.dto.UserUpdatePasswordDto;
import com.simplesdental.infra.user.persistence.UserRepository;
import com.simplesdental.infra.user.persistence.UserRole;

@WebMvcTest(value = UserController.class, excludeAutoConfiguration = { SecurityAutoConfiguration.class,
		SecurityFilterAutoConfiguration.class })
class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private UserRegisterGateway userRegisterGateway;

	@MockBean
	private UserLoginGateway userLoginGateway;

	@MockBean
	private UserContextGateway userContextGateway;

	@MockBean
	private UserUpdatePasswordGateway userUpdatePasswordGateway;

	@MockBean
	private UserRepository userRepository;

	private UserLoginDto userLoginDto;
	private UserContext userContext;
	private UserRegisterDto userRegisterDto;
	private UserUpdatePasswordDto userUpdatePasswordDto;

	@BeforeEach
	void setUp() {
		userLoginDto = new UserLoginDto();
		userLoginDto.setEmail("user@example.com");
		userLoginDto.setPassword("password123");

		userContext = new UserContext();
		userContext.setId(1L);
		userContext.setEmail("user@example.com");
		userContext.setRole(UserRole.USER);

		userRegisterDto = new UserRegisterDto();
		userRegisterDto.setName("User Example");
		userRegisterDto.setEmail("user@example.com");
		userRegisterDto.setPassword("password123");

		userUpdatePasswordDto = new UserUpdatePasswordDto();
		userUpdatePasswordDto.setActualPassword("password123");
		userUpdatePasswordDto.setNewPassword("novapassword123");
	}

	@Test
	void shouldLoginSuccessfully() throws Exception {
		when(userLoginGateway.execute(any(UserLoginDto.class))).thenReturn(userContext);

		mockMvc.perform(post("/api/user/auth/login").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(userLoginDto))).andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(userContext.getId()))
				.andExpect(jsonPath("$.email").value(userContext.getEmail()))
				.andExpect(jsonPath("$.role").value(userContext.getRole().name()));
	}

	@Test
	void shouldRegisterUser() throws Exception {
		mockMvc.perform(post("/api/user/auth/register").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(userRegisterDto))).andExpect(status().isCreated());
	}

	@Test
	void shouldReturnUserContext() throws Exception {
		Authentication authentication = Mockito.mock(Authentication.class);
		when(authentication.getName()).thenReturn("user@example.com");

		SecurityContext securityContext = Mockito.mock(SecurityContext.class);
		when(securityContext.getAuthentication()).thenReturn(authentication);

		SecurityContextHolder.setContext(securityContext);
		when(userContextGateway.execute(eq("user@example.com"))).thenReturn(userContext);

		mockMvc.perform(get("/api/user/auth/context")).andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(userContext.getId()))
				.andExpect(jsonPath("$.email").value(userContext.getEmail()))
				.andExpect(jsonPath("$.role").value(userContext.getRole().name()));
	}

	@Test
	void shouldUpdatePasswordSuccessfully() throws Exception {
		doNothing().when(userUpdatePasswordGateway).execute(any(UserUpdatePasswordDto.class));

		mockMvc.perform(put("/api/user/users/password").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(userUpdatePasswordDto))).andExpect(status().isNoContent());
	}
}
