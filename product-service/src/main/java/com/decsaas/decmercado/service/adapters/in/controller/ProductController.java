package com.decsaas.decmercado.service.adapters.in.controller;

import com.decsaas.decmercado.service.adapters.in.controller.request.EditProductRequest;
import com.decsaas.decmercado.service.adapters.in.controller.request.InsertProductRequest;
import com.decsaas.decmercado.service.adapters.in.controller.response.ProductResponse;
import com.decsaas.decmercado.service.adapters.in.error.ErrorResponse;
import com.decsaas.decmercado.service.application.core.domain.Product;
import com.decsaas.decmercado.service.application.ports.in.EditProductInputPort;
import com.decsaas.decmercado.service.application.ports.in.FindProductInputPort;
import com.decsaas.decmercado.service.application.ports.in.InsertProductInputPort;
import com.decsaas.decmercado.service.application.ports.in.RemoveProductInputPort;
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

import java.util.List;
import java.util.Map;

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
    private RemoveProductInputPort removeProductInputPort;

    @Autowired
    private FindProductInputPort findProductInputPort;

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
            @RequestHeader Map<?, ?> requestHeader,
            @Valid @RequestBody InsertProductRequest insertProductRequest) {
        return Mono.create(monoSink -> {
            Product product = productMapper.toProduct(
                    requestHeader.get("userId").toString(),
                    insertProductRequest
            );
            Product productInserted = insertProductInputPort.insert(product);
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
                    description = "Produto não existe.",
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
    public Mono<ProductResponse> edit(
            @RequestHeader Map<?, ?> requestHeader,
            @Valid @RequestBody EditProductRequest editProductRequest) {
        return Mono.create(monoSink -> {
            Product product = productMapper.toProduct(
                    requestHeader.get("userId").toString(),
                    editProductRequest
            );
            Product productEdited = editProductInputPortd.edit(product);
            monoSink.success(productMapper.toProductResponse(productEdited));
        });
    }

    @Operation(summary = "Deletar um produto",
            description = "Responsável por deletar um produto dado um id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto deletado."),
            @ApiResponse(responseCode = "404",
                    description = "Produto não existe.",
                    content = {
                            @Content(mediaType = "application/json", schema =
                            @Schema(implementation = ErrorResponse.class))
                    }),
    })
    @DeleteMapping("deletar/{id}")
    public Mono<ResponseEntity<String>> remove(
            @RequestHeader Map<?, ?> requestHeader,
            @PathVariable String id
    ) {
        return Mono.create(monoSink -> {
            String userId = requestHeader.get("userId").toString();
            removeProductInputPort.remove(userId, id);
            monoSink.success(
                    new ResponseEntity<>(
                            HttpStatus.OK
                    )
            );
        });
    }

    @Operation(summary = "Obter um produto",
            description = "Responsável por obter um produto dado um id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna dados do produto."),
            @ApiResponse(responseCode = "404",
                    description = "Produto não existe.",
                    content = {
                            @Content(mediaType = "application/json", schema =
                            @Schema(implementation = ErrorResponse.class))
                    }),
    })
    @GetMapping("obter/{id}")
    public Mono<ResponseEntity<ProductResponse>> get(
            @RequestHeader Map<?, ?> requestHeader,
            @PathVariable String id
    ) {
        return Mono.create(monoSink -> {
            String userId = requestHeader.get("userId").toString();
            Product product = findProductInputPort.findById(userId, id);
            monoSink.success(
                    new ResponseEntity<>(productMapper.toProductResponse(product),
                            HttpStatus.OK
                    )
            );
        });
    }

    @Operation(summary = "Obter todos os produto",
            description = "Responsável por obter todos os produtos da base")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna uma lista de produtos."),
    })
    @GetMapping("obter")
    public Mono<ResponseEntity<List<ProductResponse>>> getAll(@RequestHeader Map<?, ?> requestHeader) {
        return Mono.create(monoSink -> {
            String userId = requestHeader.get("userId").toString();
            List<Product> list = findProductInputPort.findAll(userId);
            monoSink.success(
                    new ResponseEntity<>(list.stream()
                            .map(product -> productMapper.toProductResponse(product))
                            .toList(),
                            HttpStatus.OK
                    )
            );
        });
    }
}
