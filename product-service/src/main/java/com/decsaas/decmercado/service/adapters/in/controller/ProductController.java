package com.decsaas.decmercado.service.adapters.in.controller;

import com.decsaas.decmercado.service.application.ports.in.InsertProductInputPort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@Tag(name = "Produtos")
@RestController
@RequestMapping("/v1/produto")
public class ProductController {

    @Autowired
    private InsertProductInputPort insertProductInputPort;

    @Autowired
    private ProductMapper productMapper;

    @Operation(summary = "Descricao do endpoint")
    @PostMapping("inserir")
    public ResponseEntity<Void> insert(@Valid @RequestBody ProductRequest productRequest) {
        insertProductInputPort.insert(productMapper.toProduct(productRequest));
        return ResponseEntity.created(URI.create("local")).build();
    }
}
