package com.decsaas.decmercado.service.adapters.in.controller;

import com.decsaas.decmercado.service.adapters.in.controller.request.EditProductRequest;
import com.decsaas.decmercado.service.adapters.in.controller.request.InsertProductRequest;
import com.decsaas.decmercado.service.adapters.in.controller.response.ProductResponse;
import com.decsaas.decmercado.service.adapters.in.error.ErrorResponse;
import com.decsaas.decmercado.service.application.core.domain.Product;
import com.decsaas.decmercado.service.application.ports.in.EditProductInputPort;
import com.decsaas.decmercado.service.application.ports.in.InsertProductInputPort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

//TODO: permitir a doc a partir do Gateway

@RestController
@RequestMapping("/v1/produto")
@Tag(name = "Produtos - v1", description = "CRUD da API de produtos")
public class ProductController {

    @Autowired
    private InsertProductInputPort insertProductInputPort;

    @Autowired
    private EditProductInputPort editProductInputPortd;

    @Autowired
    private ProductMapper productMapper;

    @Operation(summary = "Inserção de produtos",
            description = "Responsável por gravar os produtos")
    @PostMapping("inserir")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Produto salvo."),
            @ApiResponse(responseCode = "400",
                    description = "Atributo descrição não informado ou vazio na request.",
                    content = {
                            @Content(mediaType = "application/json", schema =
                            @Schema(implementation = ErrorResponse.class))
                    }),
            @ApiResponse(responseCode = "409",
                    description = "Produto com a mesma descrição já existe.",
                    content = {
                            @Content(mediaType = "application/json", schema =
                            @Schema(implementation = ErrorResponse.class))
                    }),
    })
    public Mono<ResponseEntity<ProductResponse>> insert(
            @Valid @RequestBody InsertProductRequest insertProductRequest) {
        return Mono.create(monoSink -> {
            Product productInserted = insertProductInputPort.insert(productMapper.toProduct(insertProductRequest));
            monoSink.success(
                    new ResponseEntity<>(productMapper.toProductResponse(productInserted),
                            HttpStatus.CREATED
                    )
            );
        });
    }

    @Operation(summary = "Edição de um produto",
            description = "Responsável por editar a descrição de um produto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto editado."),
            @ApiResponse(responseCode = "400",
                    description = "Atributo id ou descrição não informado ou vazio na request.",
                    content = {
                            @Content(mediaType = "application/json", schema =
                            @Schema(implementation = ErrorResponse.class))
                    }),
            @ApiResponse(responseCode = "404",
                    description = "ID informado não existe.",
                    content = {
                            @Content(mediaType = "application/json", schema =
                            @Schema(implementation = ErrorResponse.class))
                    }),
            @ApiResponse(responseCode = "409",
                    description = "Produto com a mesma descrição já existe.",
                    content = {
                            @Content(mediaType = "application/json", schema =
                            @Schema(implementation = ErrorResponse.class))
                    }),
    })
    @PutMapping("editar")
    public Mono<ProductResponse> edit(@Valid @RequestBody EditProductRequest editProductRequest) {
        return Mono.create(monoSink -> {
            Product productEdited = editProductInputPortd.edit(productMapper.toProduct(editProductRequest));
            monoSink.success(productMapper.toProductResponse(productEdited));
        });
    }

    //TODO: criar outros endpoint
}
