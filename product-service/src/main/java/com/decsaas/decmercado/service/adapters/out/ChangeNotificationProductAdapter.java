package com.decsaas.decmercado.service.adapters.out;

import com.decsaas.decmercado.service.application.core.domain.Product;
import com.decsaas.decmercado.service.application.ports.out.ChangeNotificationProductOutputPort;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.decsaas.decmercado.service.config.RabbitMQConfig.DIRECT_NAME;
import static com.decsaas.decmercado.service.config.RabbitMQConfig.ROUTING_NAME;

@Component
public class ChangeNotificationProductAdapter implements ChangeNotificationProductOutputPort {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public void sendChange(Product product) {
        rabbitTemplate.convertAndSend(DIRECT_NAME, ROUTING_NAME, product);
    }
}
