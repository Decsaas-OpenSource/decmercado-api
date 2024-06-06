package com.decsaas.decmercado.service.adapters.in.ampq;

import com.decsaas.decmercado.service.adapters.out.repository.MyListRepository;
import jakarta.transaction.Transactional;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.decsaas.decmercado.service.config.RabbitMQConfig.QUEUE_NAME;

@Service
@Transactional
public class UpdateBatchProductAMPQ {

    @Autowired
    private MyListRepository myListRepository;

    @RabbitListener(queues = {QUEUE_NAME})
    public void update(Product product) {
        myListRepository.updateProductionDescription(product.getDescription(),
                product.getId(),
                product.getUserId());
    }
}
