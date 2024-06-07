package com.decsaas.decmercado.service.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitMQConfig {

    public static final String QUEUE_NAME = "produto.v1.alteracao";
    public static final String DIRECT_NAME = "produto.direct";
    public static final String ROUTING_NAME = "produto.api";

    public static final String QUEUE_NAME_DLQ = "produto.v1.alteracao.dlq";
    public static final String DIRECT_NAME_DLX = "produto.direct.dlx";
    public static final String ROUTING_NAME_DLQ = "produto.api.dlq";

    @Bean
    public Queue queue() {
        Map<String, Object> args = new HashMap<>();
        args.put("x-dead-letter-exchange", DIRECT_NAME_DLX);
        args.put("x-dead-letter-routing-key", ROUTING_NAME_DLQ);
        return new Queue(QUEUE_NAME, true, false, false, args);
    }

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(DIRECT_NAME);
    }

    @Bean
    public Queue queueDLQ() {
        return new Queue(QUEUE_NAME_DLQ);
    }

    @Bean
    public DirectExchange directExchangeDLX() {
        return new DirectExchange(DIRECT_NAME_DLX);
    }

    @Bean
    public Binding binding() {
        return BindingBuilder.
                bind(queue()).to(directExchange()).with(ROUTING_NAME);
    }

    @Bean
    public Binding bindingDLQ() {
        return BindingBuilder.
                bind(queueDLQ()).to(directExchangeDLX()).with(ROUTING_NAME_DLQ);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        ObjectMapper objectMapper = new ObjectMapper();
        return new Jackson2JsonMessageConverter(objectMapper);
    }

}
