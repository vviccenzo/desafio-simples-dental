package com.simplesdental.product.controller.product;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
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
import com.simplesdental.application.product.gateways.CreateProductGateway;
import com.simplesdental.application.product.gateways.DeleteProductByIdGateway;
import com.simplesdental.application.product.gateways.GetAllProductGateway;
import com.simplesdental.application.product.gateways.GetProductByIdGateway;
import com.simplesdental.application.product.gateways.UpdateProductGateway;
import com.simplesdental.domain.product.entities.Product;
import com.simplesdental.infra.product.controller.ProductV2Controller;
import com.simplesdental.infra.product.dto.ProductCreateDto;
import com.simplesdental.infra.product.dto.ProductUpdateDto;
import com.simplesdental.infra.user.persistence.UserRepository;

@WebMvcTest(value = ProductV2Controller.class, excludeAutoConfiguration = { SecurityAutoConfiguration.class,
		SecurityFilterAutoConfiguration.class })
class ProductV2ControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private GetAllProductGateway getAllProductGateway;

	@MockBean
	private GetProductByIdGateway getProductByIdGateway;

	@MockBean
	private CreateProductGateway createProductGateway;

	@MockBean
	private UpdateProductGateway updateProductGateway;

	@MockBean
	private DeleteProductByIdGateway deleteProductByIdGateway;

	@MockBean
	private UserRepository userRepository;

	private Product product;
	private ProductCreateDto productCreateDto;
	private ProductUpdateDto productUpdateDto;

	@BeforeEach
	void setUp() {
		product = new Product();
		product.setId(1L);
		product.setName("Test Product");
		product.setDescription("Test Description");
		product.setPrice(BigDecimal.valueOf(10.99));

		productCreateDto = new ProductCreateDto();
		productCreateDto.setName("Test Product");
		productCreateDto.setDescription("Test Description");
		productCreateDto.setPrice(BigDecimal.valueOf(10.99));
		productCreateDto.setCode(123);
		productCreateDto.setCategoryId(1L);

		productUpdateDto = new ProductUpdateDto();
		productUpdateDto.setName("Product Updated");
		productUpdateDto.setDescription("Description Updated");
		productUpdateDto.setPrice(BigDecimal.valueOf(19.99));
		productUpdateDto.setCode(123);
	}

	@Test
	void shouldGetAllProducts() throws Exception {
		Pageable pageable = PageRequest.of(0, 10);
		Page<Product> page = new PageImpl<>(List.of(product));
		when(getAllProductGateway.execute(pageable)).thenReturn(page);

		mockMvc.perform(get("/api/v2/products").param("page", "0").param("size", "10")).andExpect(status().isOk())
				.andExpect(jsonPath("$.content[0].id").value(product.getId()))
				.andExpect(jsonPath("$.content[0].name").value(product.getName()));
	}

	@Test
	void shouldGetProductById() throws Exception {
		when(getProductByIdGateway.execute(1L)).thenReturn(Optional.of(product));

		mockMvc.perform(get("/api/v2/products/1")).andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(product.getId()))
				.andExpect(jsonPath("$.name").value(product.getName()))
				.andExpect(jsonPath("$.description").value(product.getDescription()));
	}

	@Test
	void shouldReturn404WhenGetProductByIdNotFound() throws Exception {
		when(getProductByIdGateway.execute(1L)).thenReturn(Optional.empty());

		mockMvc.perform(get("/api/v2/products/1")).andExpect(status().isNotFound());
	}

	@Test
	void shouldCreateProduct() throws Exception {
		when(createProductGateway.execute(any(ProductCreateDto.class))).thenReturn(product);

		mockMvc.perform(post("/api/v2/products").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(productCreateDto))).andExpect(status().isCreated())
				.andExpect(jsonPath("$.id").value(product.getId()))
				.andExpect(jsonPath("$.name").value(product.getName()))
				.andExpect(jsonPath("$.description").value(product.getDescription()));
	}

	@Test
	void shouldUpdateProduct() throws Exception {
		when(updateProductGateway.execute(eq(1L), any(ProductUpdateDto.class))).thenReturn(product);

		mockMvc.perform(put("/api/v2/products/1").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(productUpdateDto))).andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(product.getId()))
				.andExpect(jsonPath("$.name").value(product.getName()))
				.andExpect(jsonPath("$.description").value(product.getDescription()));
	}

	@Test
	void shouldDeleteProduct() throws Exception {
		when(deleteProductByIdGateway.execute(1L)).thenReturn(true);

		mockMvc.perform(delete("/api/v2/products/1")).andExpect(status().isNoContent());
	}

	@Test
	void shouldReturn404WhenDeleteProductNotFound() throws Exception {
		when(deleteProductByIdGateway.execute(1L)).thenReturn(false);

		mockMvc.perform(delete("/api/v2/products/1")).andExpect(status().isNotFound());
	}
}
