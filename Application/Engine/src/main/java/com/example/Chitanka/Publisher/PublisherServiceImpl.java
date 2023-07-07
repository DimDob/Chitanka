package com.example.Chitanka.Publisher;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.avro.specific.SpecificRecord;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PublisherServiceImpl implements PublisherService {

    @Value("${rabbitmq.exchange.name}")
    private String topicExchange;

    @Value("${rabbitmq.queue.added}")
    private String addedBookQueue;

    @Value("${rabbitmq.queue.loaned}")
    private String loanedBookQueue;

    @Value("${rabbitmq.queue.deleted}")
    private String deletedBookQueue;

    @Value("${rabbitmq.routing.key.onAdd}")
    private String onAddRoutingKey;

    @Value("${rabbitmq.routing.key.onLoan}")
    private String onLoanRoutingKey;

    @Value("${rabbitmq.routing.key.onDelete}")
    private String onDeleteRoutingKey;


    private RabbitTemplate rabbitTemplate;


    public PublisherServiceImpl(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publish(SpecificRecord event) {
        String toQueue = event.getSchema().getName();
        switch (toQueue) {
            case "BookAdded" -> rabbitTemplate.convertAndSend(topicExchange, onAddRoutingKey, event);
            case "BookLoaned" -> rabbitTemplate.convertAndSend(topicExchange, onLoanRoutingKey, event);
            case "BookDeleted" -> rabbitTemplate.convertAndSend(topicExchange, onDeleteRoutingKey, event);
        }
    }
}
