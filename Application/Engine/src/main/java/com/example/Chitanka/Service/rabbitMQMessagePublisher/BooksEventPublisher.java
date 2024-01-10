package com.example.Chitanka.Service.rabbitMQMessagePublisher;

import com.example.Chitanka.Entity.Book;
import com.example.Chitanka.Entity.User;
import com.example.Chitanka.Publisher.PublisherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import schemas.messaging.avro.events.books.added.BookAdded;
import schemas.messaging.avro.events.books.deleted.BookDeleted;
import schemas.messaging.avro.events.books.loaned.BookLoaned;

@Component
@RequiredArgsConstructor
public class BooksEventPublisher {

    private final PublisherService publisherService;

    @Transactional
    public void publishBookAdded(Book book) {
        BookAdded bookAdded = BookAdded.newBuilder()
                .setId(book.getId().toString())
                .setTitle(book.getTitle())
                .setAuthor(String.valueOf(book.getAuthor()))
                .setLink(book.getLink())
                .setSubject(book.getSubject())
                .build();
        publisherService.publish(bookAdded);
    }

    @Transactional
    public void publishBookLoaned(Book book) {
        BookLoaned bookLoaned = BookLoaned.newBuilder()
                .setId(book.getId().toString())
                .setIsLoaned(true)
                .setAuthor(book.getAuthor().toString())
                .setLink(book.getLink())
                .setSubject(book.getSubject())
                .setTitle(book.getTitle())
                .setPatronEmail("dimitardobrev.business@gmail.com") //TODO will be taken from input form
                .build();
        publisherService.publish(bookLoaned);
    }

    @Transactional
    public void publishBookDeleted(Book book){
        BookDeleted bookDeleted = BookDeleted.newBuilder()
                .setId(book.getId().toString())
                .setIsDeleted(true)
                .setAuthor(book.getAuthor().toString())
                .setLink(book.getLink())
                .setSubject(book.getSubject())
                .build();
        publisherService.publish(bookDeleted);
    }




}
