package com.decsaas.decmercado.service.adapters.in.controller;

import com.decsaas.decmercado.service.adapters.in.controller.request.InsertMyListRequest;
import com.decsaas.decmercado.service.adapters.in.controller.request.WebHookResquest;
import com.decsaas.decmercado.service.adapters.in.controller.response.MyListResponse;
import com.decsaas.decmercado.service.application.core.domain.MyList;
import com.decsaas.decmercado.service.application.ports.in.InsertMyListInputPort;
import com.decsaas.decmercado.service.application.ports.in.UpdateBatchProductInputPort;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequestMapping("/v1")
public class MyListController {

    @Autowired
    private MyListMapper myListMapper;

    @Autowired
    private InsertMyListInputPort insertMyListInputPort;

    @Autowired
    private UpdateBatchProductInputPort updateBatchProductInputPort;

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

    /**
     * Os outros endpoints CRUD são mais do mesmo, por isso não vou cria-los
     */

    /**
     * WebHook para atualizar a descricao do produto na lista
     *
     * @param webHookResquest
     * @return
     */
    @PostMapping("webhook")
    public Mono<ResponseEntity<Void>> webhook(
            @Valid @RequestBody WebHookResquest webHookResquest) {
        return Mono.create(monoSink -> {
            updateBatchProductInputPort.update(
                    webHookResquest.getUserId(),
                    webHookResquest.getProductId(),
                    webHookResquest.getProductDescription()
            );

            monoSink.success(
                    new ResponseEntity<>(
                            HttpStatus.OK
                    )
            );
        });
    }


}
