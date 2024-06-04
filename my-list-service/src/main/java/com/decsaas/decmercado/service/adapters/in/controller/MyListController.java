package com.decsaas.decmercado.service.adapters.in.controller;

import com.decsaas.decmercado.service.adapters.in.controller.request.InsertMyListRequest;
import com.decsaas.decmercado.service.adapters.in.controller.response.MyListResponse;
import com.decsaas.decmercado.service.application.core.domain.MyList;
import com.decsaas.decmercado.service.application.ports.in.InsertMyListInputPort;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequestMapping("/v1/minha-lista")
public class MyListController {

    @Autowired
    private MyListMapper myListMapper;

    @Autowired
    private InsertMyListInputPort insertMyListInputPort;

    @PostMapping("inserir")
    public Mono<ResponseEntity<MyListResponse>> insert(
            @RequestHeader Map<?, ?> requestHeader,
            @Valid @RequestBody InsertMyListRequest insertProductRequest) {
        return Mono.create(monoSink -> {
            MyList myList = myListMapper.toMyList(
                    requestHeader.get("userId").toString(),
                    insertProductRequest
            );

            MyList result = insertMyListInputPort.insert(myList);
            monoSink.success(
                    new ResponseEntity<>(myListMapper.toProductResponse(result),
                            HttpStatus.CREATED
                    )
            );
        });
    }
}
