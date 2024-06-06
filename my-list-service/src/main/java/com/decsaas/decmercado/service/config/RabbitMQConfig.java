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

@Configuration
public class RabbitMQConfig {

    //TODO: Ideal é ter um projeto common, mas para não criar complexidade no exemplo, não será criado
    public static final String QUEUE_NAME = "produto.v1.alteracao";
    public static final String DIRECT_NAME = "produto.direct";
    public static final String ROUTING_NAME = "produto.api";

    @Bean
    public Queue queue() {
        return new Queue(QUEUE_NAME);
    }

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(DIRECT_NAME);
    }

    @Bean
    public Binding binding() {
        return BindingBuilder.
                bind(queue()).to(directExchange()).with(ROUTING_NAME);

    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        ObjectMapper objectMapper = new ObjectMapper();
        return new Jackson2JsonMessageConverter(objectMapper);
    }

}
