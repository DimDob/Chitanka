package com.example.RabbitMQ.Consumer;

import com.example.Deserializer.Deserializer;
import com.example.Service.NotificationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.avro.specific.SpecificRecord;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import schemas.messaging.avro.events.books.deleted.BookDeleted;
import schemas.messaging.avro.events.books.loaned.BookLoaned;

@Service
@Slf4j
public class ConsumerService {

    @Value("${rabbitmq.queue.added}")
    private String addedBookQueue;

    @Value("${rabbitmq.queue.loaned}")
    private String loanedBookQueue;

    @Value("${rabbitmq.queue.deleted}")
    private String deletedBookQueue;

    @Autowired
    Deserializer deserializer;

    @Autowired
    EmailSender emailSender;

    @Autowired
    NotificationService notificationService;

    @RabbitListener(queues = {"${rabbitmq.queue.added}", "${rabbitmq.queue.loaned}", "${rabbitmq.queue.deleted}"})
    public void consumeEvent(SpecificRecord event) {
        String registeredEvent = "Event registered for book -  " + event.toString() + " : [event={ " + event.getSchema().getName() + " }]";
        log.info(registeredEvent);
        String fromQueue = event.getSchema().getName();
        switch (fromQueue) {
            case "BookAdded" -> {
                log.info("Event received [event={}]", fromQueue);
                log.info("Event consumed [event={}] in queue [queue={}]", event, addedBookQueue);


                //TODO fix when sending mail after event is consumed
//                BookAdded deserializedMessage = deserializer.deserializeBookAdded(event);
//                String greetMessage = notificationService.sendGreeting(event).get();
//                String title = String.valueOf(deserializedMessage.get("title"));
//                String author = String.valueOf(deserializedMessage.get("author"));
//                String content = EmailTemplate.BOOK_ADDED.getTemplate() + author;
//
//                emailSender.sendMail(title, greetMessage, content);

            }
            case "BookLoaned" -> {
                log.info("Event received [event={}]", fromQueue);
                log.info("Event consumed [event={}] in queue [queue={}]", event, loanedBookQueue);

                BookLoaned deserializedMessage = deserializer.deserializeBookModified(event);

                String greetMessage = notificationService.sendGreeting(event).get();

                 String library_member_email = String.valueOf(deserializedMessage.get("patron_email"));


                String content = EmailTemplate.BOOK_LOANED.getTemplate() + GenerateContentMessages.bookLoanedContentMessage(deserializedMessage);

                emailSender.sendMail(library_member_email, greetMessage,  content);

            }
            case "BookDeleted" -> {
                log.info("Event received [event={}]", fromQueue);
                log.info("Event consumed [event={}] in queue [queue={}]", event, deletedBookQueue);

                BookDeleted deserializedMessage = deserializer.deserializeBookDeleted(event);

                String greetMessage = notificationService.sendGreeting(event).get();
                String title = String.valueOf(deserializedMessage.get("title"));
                String author = String.valueOf(deserializedMessage.get("author"));
                String content = EmailTemplate.BOOK_DELETED.getTemplate() + author;

                emailSender.sendMail(title, greetMessage, content);
            }
        }
    }
}
