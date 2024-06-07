package com.decsaas.decmercado.service.adapters.out;

import com.decsaas.decmercado.service.adapters.out.webhook.MyListProxy;
import com.decsaas.decmercado.service.adapters.out.webhook.WebHookResquest;
import com.decsaas.decmercado.service.application.core.domain.Product;
import com.decsaas.decmercado.service.application.ports.out.ChangeNotificationProductOutputPort;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.decsaas.decmercado.service.config.RabbitMQConfig.*;

@Component
public class ChangeNotificationProductAdapter implements ChangeNotificationProductOutputPort {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private MyListProxy myListProxy;

    @Override
    public void sendChange(Product product) {
        rabbitTemplate.convertAndSend(DIRECT_NAME, ROUTING_NAME, product);
    }

    @RabbitListener(queues = {QUEUE_NAME})
    public void update(Product product) {
        WebHookResquest webHookResquest = new WebHookResquest();
        webHookResquest.setUserId(product.getUserId());
        webHookResquest.setProductId(product.getId());
        webHookResquest.setProductDescription(product.getDescription());
        myListProxy.webhook(webHookResquest);
    }
}
