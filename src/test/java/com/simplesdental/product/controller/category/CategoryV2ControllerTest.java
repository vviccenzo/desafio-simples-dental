package com.simplesdental.product.controller.category;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.simplesdental.application.category.gateway.CreateCategoryGateway;
import com.simplesdental.application.category.gateway.DeleteCategoryByIdGateway;
import com.simplesdental.application.category.gateway.GetAllCategoryGateway;
import com.simplesdental.application.category.gateway.GetCategoryByIdGateway;
import com.simplesdental.application.category.gateway.UpdateCategoryGateway;
import com.simplesdental.domain.category.entities.Category;
import com.simplesdental.infra.category.controller.CategoryV2Controller;
import com.simplesdental.infra.category.dto.CategoryCreateDto;
import com.simplesdental.infra.category.dto.CategoryUpdateDto;
import com.simplesdental.infra.user.persistence.UserRepository;

@WebMvcTest(value = CategoryV2Controller.class, excludeAutoConfiguration = { SecurityAutoConfiguration.class,
		SecurityFilterAutoConfiguration.class })
class CategoryV2ControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private GetAllCategoryGateway getAllCategoryGateway;

	@MockBean
	private GetCategoryByIdGateway findByIdCategoryGateway;

	@MockBean
	private CreateCategoryGateway createCategoryGateway;

	@MockBean
	private UpdateCategoryGateway updateCategoryGateway;

	@MockBean
	private DeleteCategoryByIdGateway deleteCategoryByIdGateway;

	@MockBean
	private UserRepository userRepository;

	@Autowired
	private ObjectMapper objectMapper;

	private Category category;
	private CategoryCreateDto categoryCreateDto;
	private CategoryUpdateDto categoryUpdateDto;

	@BeforeEach
	void setUp() {
		category = new Category();
		category.setId(1L);
		category.setName("Test Category");
		category.setDescription("Test Description");

		categoryCreateDto = new CategoryCreateDto("Test Category", "Test Description", List.of());
		categoryUpdateDto = new CategoryUpdateDto(1L, "Updated Category", "Updated Description");
	}

	@Test
	void shouldGetAllCategories() throws Exception {
		Pageable pageable = PageRequest.of(0, 10);
		Page<Category> page = new PageImpl<>(List.of(category));
		when(getAllCategoryGateway.execute(pageable)).thenReturn(page);

		mockMvc.perform(get("/api/v2/categories").param("page", "0").param("size", "10")).andExpect(status().isOk())
				.andExpect(jsonPath("$.content[0].id").value(category.getId()))
				.andExpect(jsonPath("$.content[0].name").value(category.getName()));
	}

	@Test
	void shouldGetCategoryById() throws Exception {
		when(findByIdCategoryGateway.execute(1L)).thenReturn(Optional.of(category));

		mockMvc.perform(get("/api/v2/categories/1")).andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(category.getId()))
				.andExpect(jsonPath("$.name").value(category.getName()));
	}

	@Test
	void shouldReturn404WhenGetCategoryByIdNotFound() throws Exception {
		when(findByIdCategoryGateway.execute(1L)).thenReturn(Optional.empty());

		mockMvc.perform(get("/api/v2/categories/1")).andExpect(status().isNotFound());
	}

	@Test
	void shouldCreateCategory() throws Exception {
		when(createCategoryGateway.execute(any(CategoryCreateDto.class))).thenReturn(category);

		mockMvc.perform(post("/api/v2/categories").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(categoryCreateDto))).andExpect(status().isCreated())
				.andExpect(jsonPath("$.id").value(category.getId()))
				.andExpect(jsonPath("$.name").value(category.getName()));
	}

	@Test
	void shouldUpdateCategory() throws Exception {
		when(updateCategoryGateway.execute(eq(1L), any(CategoryUpdateDto.class))).thenReturn(category);

		mockMvc.perform(put("/api/v2/categories/1").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(categoryUpdateDto))).andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(category.getId()))
				.andExpect(jsonPath("$.name").value(category.getName()));
	}

	@Test
	void shouldDeleteCategory() throws Exception {
		when(deleteCategoryByIdGateway.execute(1L)).thenReturn(true);

		mockMvc.perform(delete("/api/v2/categories/1")).andExpect(status().isNoContent());
	}

	@Test
	void shouldReturn404WhenDeleteCategoryNotFound() throws Exception {
		when(deleteCategoryByIdGateway.execute(1L)).thenReturn(false);

		mockMvc.perform(delete("/api/v2/categories/1")).andExpect(status().isNotFound());
	}
}