package com.simplesdental.infra.product.controller;

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

import com.simplesdental.application.product.gateways.CreateProductGateway;
import com.simplesdental.application.product.gateways.DeleteProductByIdGateway;
import com.simplesdental.application.product.gateways.GetAllProductGateway;
import com.simplesdental.application.product.gateways.GetProductByIdGateway;
import com.simplesdental.application.product.gateways.UpdateProductGateway;
import com.simplesdental.domain.product.entities.Product;
import com.simplesdental.infra.product.dto.ProductCreateDto;
import com.simplesdental.infra.product.dto.ProductUpdateDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v2/products")
@Tag(name = "Produtos v2", description = "Endpoints relacionados a operações com produtos v2")
public class ProductV2Controller {

        private final GetAllProductGateway getAllProductGateway;
        private final GetProductByIdGateway getProductByIdGateway;
        private final CreateProductGateway createProductGateway;
        private final UpdateProductGateway updateProductGateway;
        private final DeleteProductByIdGateway deleteProductByIdGateway;

        public ProductV2Controller(
                        GetAllProductGateway getAllProductGateway, GetProductByIdGateway getProductByIdGateway,
                        CreateProductGateway createProductGateway, UpdateProductGateway updateProductGateway,
                        DeleteProductByIdGateway deleteProductByIdGateway) {
                this.getAllProductGateway = getAllProductGateway;
                this.getProductByIdGateway = getProductByIdGateway;
                this.createProductGateway = createProductGateway;
                this.updateProductGateway = updateProductGateway;
                this.deleteProductByIdGateway = deleteProductByIdGateway;
        }

        @GetMapping
        @ResponseStatus(HttpStatus.OK)
        @Operation(summary = "Recupera todos os produtos", description = "Retorna uma lista paginada de produtos disponíveis no sistema.", responses = {
                        @ApiResponse(responseCode = "200", description = "Lista de produtos recuperada com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Page.class))),
                        @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
        })
        public Page<Product> getAllProducts(@Valid Pageable pageable) {
                return this.getAllProductGateway.execute(pageable);
        }

        @GetMapping("/{id}")
        @ResponseStatus(HttpStatus.OK)
        @Operation(summary = "Recupera um produto pelo ID", description = "Retorna o produto correspondente ao ID informado. Caso o produto não seja encontrado, retorna 404.", parameters = {
                        @Parameter(name = "id", description = "ID do produto", required = true)
        }, responses = {
                        @ApiResponse(responseCode = "200", description = "Produto encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Product.class))),
                        @ApiResponse(responseCode = "404", description = "Produto não encontrado"),
                        @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
        })
        public ResponseEntity<Product> getProductById(@PathVariable Long id) {
                return this.getProductByIdGateway.execute(id).map(ResponseEntity::ok)
                                .orElse(ResponseEntity.notFound().build());
        }

        @PostMapping
        @ResponseStatus(HttpStatus.CREATED)
        @Operation(summary = "Cria um novo produto", description = "Cria um novo produto com os dados fornecidos. O corpo da requisição deve seguir o schema definido.", requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, description = "Objeto JSON contendo os dados do produto a ser criado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProductCreateDto.class), examples = {
                        @ExampleObject(name = "Exemplo de Criação", value = "{ \"name\": \"Produto A\", \"description\": \"Descrição do produto A\", \"price\": 10.99, \"status\": true, \"code\": 1 }")
        })), responses = {
                        @ApiResponse(responseCode = "201", description = "Produto criado com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Product.class))),
                        @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos na requisição"),
                        @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
        })
        public Product createProduct(@Valid @RequestBody ProductCreateDto product) {
                return this.createProductGateway.execute(product);
        }

        @PutMapping("/{id}")
        @ResponseStatus(HttpStatus.OK)
        @Operation(summary = "Atualiza um produto existente", description = "Atualiza os dados de um produto existente, identificado pelo seu ID.", parameters = {
                        @io.swagger.v3.oas.annotations.Parameter(name = "id", description = "ID do produto a ser atualizado", required = true)
        }, requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, description = "Objeto JSON contendo os dados atualizados para o produto", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProductUpdateDto.class), examples = {
                        @ExampleObject(name = "Exemplo de Atualização", value = "{ \"name\": \"Produto A Atualizado\", \"description\": \"Nova descrição\", \"price\": 12.99, \"status\": true, \"code\": 123 }")
        })), responses = {
                        @ApiResponse(responseCode = "200", description = "Produto atualizado com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Product.class))),
                        @ApiResponse(responseCode = "400", description = "Dados inválidos na requisição"),
                        @ApiResponse(responseCode = "404", description = "Produto não encontrado"),
                        @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
        })
        public Product updateProduct(@PathVariable Long id, @Valid @RequestBody ProductUpdateDto product) {
                return this.updateProductGateway.execute(id, product);
        }

        @DeleteMapping("/{id}")
        @ResponseStatus(HttpStatus.NO_CONTENT)
        @Operation(summary = "Exclui um produto", description = "Exclui um produto existente a partir do ID informado. Retorna 204 no sucesso e 404 se o produto não for encontrado.", parameters = {
                        @Parameter(name = "id", description = "ID do produto a ser removido", required = true)
        }, responses = {
                        @ApiResponse(responseCode = "204", description = "Produto excluído com sucesso"),
                        @ApiResponse(responseCode = "404", description = "Produto não encontrado"),
                        @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
        })
        public ResponseEntity<Void> deleteById(@PathVariable Long id) {
                return this.deleteProductByIdGateway.execute(id) ? ResponseEntity.noContent().build()
                                : ResponseEntity.notFound().build();
        }

}
