package com.example.Chitanka.Publisher.RabbitmqConfig;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${rabbitmq.queue.added}")
    private String bookAddedQueue;

    @Value("${rabbitmq.queue.loaned}")
    private String bookLoanedQueue;

    @Value("${rabbitmq.queue.deleted}")
    private String bookDeletedQueue;

    @Value("${rabbitmq.exchange.name}")
    private String topicExchange;

    @Value("${rabbitmq.routing.key.onAdd}")
    private String onAddRoutingKey;
    @Value("${rabbitmq.routing.key.onLoan}")
    private String onLoanRoutingKey;
    @Value("${rabbitmq.routing.key.onDelete}")
    private String onDeleteRoutingKey;

    @Bean
    public Queue addedQueue() {
        return new Queue(bookAddedQueue, false);
    }

    @Bean
    public Queue loanedQueue() {
        return new Queue(bookLoanedQueue, false);
    }

    @Bean
    public Queue deletedQueue() {
        return new Queue(bookDeletedQueue, false);
    }


    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(topicExchange);
    }

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(addedQueue()).to(topicExchange()).with(onAddRoutingKey);
    }

    @Bean
    public Binding bindingOnLoan() {
        return BindingBuilder.bind(loanedQueue()).to(topicExchange()).with(onLoanRoutingKey);
    }

    @Bean
    public Binding bindingOnDelete() {
        return BindingBuilder.bind(deletedQueue()).to(topicExchange()).with(onDeleteRoutingKey);
    }
}
