package com.simplesdental.infra.category.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.simplesdental.application.category.gateway.CreateCategoryGateway;
import com.simplesdental.application.category.gateway.DeleteCategoryByIdGateway;
import com.simplesdental.application.category.gateway.GetAllCategoryGateway;
import com.simplesdental.application.category.gateway.GetCategoryByIdGateway;
import com.simplesdental.application.category.gateway.UpdateCategoryGateway;
import com.simplesdental.domain.category.entities.Category;
import com.simplesdental.infra.category.dto.CategoryCreateDto;
import com.simplesdental.infra.category.dto.CategoryUpdateDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v2/categories")
@Tag(name = "Categorias v2", description = "Endpoints relacionados a operações com categorias v2")
public class CategoryV2Controller {

    private final CreateCategoryGateway createCategoryGateway;
    private final UpdateCategoryGateway updateCategoryGateway;
    private final GetAllCategoryGateway getAllCategoryGateway;
    private final GetCategoryByIdGateway findByIdCategoryGateway;
    private final DeleteCategoryByIdGateway deleteCategoryByIdGateway;

    public CategoryV2Controller(
            GetAllCategoryGateway getAllCategoryGateway,
            GetCategoryByIdGateway findByIdCategoryGateway,
            CreateCategoryGateway createCategoryGateway,
            UpdateCategoryGateway updateCategoryGateway,
            DeleteCategoryByIdGateway deleteCategoryByIdGateway) {
        this.getAllCategoryGateway = getAllCategoryGateway;
        this.findByIdCategoryGateway = findByIdCategoryGateway;
        this.createCategoryGateway = createCategoryGateway;
        this.updateCategoryGateway = updateCategoryGateway;
        this.deleteCategoryByIdGateway = deleteCategoryByIdGateway;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Recupera todas as categorias", description = "Retorna uma lista paginada de categorias disponíveis no sistema.", responses = {
            @ApiResponse(responseCode = "200", description = "Lista de categorias recuperada com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Page.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    public Page<Category> getAllCategories(@Valid Pageable pageable) {
        return this.getAllCategoryGateway.execute(pageable);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Recupera uma categoria por ID", description = "Retorna os detalhes da categoria correspondente ao ID informado. Se a categoria não for encontrada, retorna 404.", parameters = {
            @Parameter(name = "id", description = "ID da categoria", required = true)
    }, responses = {
            @ApiResponse(responseCode = "200", description = "Categoria encontrada", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Category.class))),
            @ApiResponse(responseCode = "404", description = "Categoria não encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
        return this.findByIdCategoryGateway.execute(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Cria uma nova categoria", description = "Cria uma nova categoria com os dados fornecidos. O corpo da requisição deve seguir o schema definido.", requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, description = "Objeto JSON contendo os dados da categoria a ser criada", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CategoryCreateDto.class), examples = {
            @ExampleObject(name = "Exemplo de Criação", value = "{ \"name\": \"Categoria A\", \"description\": \"Descrição da Categoria A\", \"products\": [] }")
    })), responses = {
            @ApiResponse(responseCode = "201", description = "Categoria criada com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Category.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos na requisição"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    public Category createCategory(@Valid @RequestBody CategoryCreateDto category) {
        return this.createCategoryGateway.execute(category);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Atualiza uma categoria existente", description = "Atualiza os dados de uma categoria existente, identificada pelo seu ID.", parameters = {
            @Parameter(name = "id", description = "ID da categoria a ser atualizada", required = true)
    }, requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, description = "Objeto JSON contendo os dados atualizados para a categoria", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CategoryUpdateDto.class), examples = {
            @ExampleObject(name = "Exemplo de Atualização", value = "{ \"id\": 1, \"name\": \"Categoria A Atualizada\", \"description\": \"Nova descrição da Categoria A\" }")
    })), responses = {
            @ApiResponse(responseCode = "204", description = "Categoria atualizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos na requisição"),
            @ApiResponse(responseCode = "404", description = "Categoria não encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    public void updateCategory(@PathVariable Long id, @Valid @RequestBody CategoryUpdateDto category) {
        this.updateCategoryGateway.execute(id, category);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Exclui uma categoria", description = "Exclui uma categoria existente a partir do ID informado. Retorna 204 se a categoria for removida com sucesso e 404 se não for encontrada.", parameters = {
            @Parameter(name = "id", description = "ID da categoria a ser removida", required = true)
    }, responses = {
            @ApiResponse(responseCode = "204", description = "Categoria excluída com sucesso"),
            @ApiResponse(responseCode = "404", description = "Categoria não encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        return this.deleteCategoryByIdGateway.execute(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
