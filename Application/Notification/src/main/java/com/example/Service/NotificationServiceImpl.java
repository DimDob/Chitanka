package com.example.Service;

import com.example.RabbitMQ.Consumer.EmailSender;
import com.example.RabbitMQ.Consumer.EmailTemplate;
import lombok.extern.slf4j.Slf4j;
import org.apache.avro.specific.SpecificRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
@Slf4j
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    EmailSender emailSender;

    @Override
    public Optional<String> sendGreeting(SpecificRecord event) {
//        String greetingTemplate = EmailTemplate.GREET.getTemplate();
//        String eventType = event.getSchema().getName();
//        switch (eventType) {
//            case "BookAdded" -> {
//                return Optional.of(greetingTemplate.replace("${receiver}", (((BookAdded) event).get("title").toString())));
//            } case "BookLoaned" -> {
//                return greetingTemplate.replace("${receiver}", (((BookLoaned) event).get("title").toString())).describeConstable();
//            }
//            case "BookDeleted" -> {
//                return Optional.of(greetingTemplate.replace("${receiver}", (((BookDeleted) event).get("title").toString())));
//            }
//        }
        return Optional.empty();
    }

}
