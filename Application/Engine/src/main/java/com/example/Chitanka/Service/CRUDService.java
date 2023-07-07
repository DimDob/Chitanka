package com.example.Chitanka.Service;

import com.example.Chitanka.Entity.Book;
import com.example.Chitanka.Repository.BookRepository;
import com.example.Chitanka.Service.rabbitMQMessagePublisher.BooksEventPublisher;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor
public class CRUDService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BooksEventPublisher booksEventPublisher;

    @Transactional
    public Book deleteBookById(UUID bookId) {
        Optional<Book> book = bookRepository.findById(bookId);
        if (book.isPresent()) {
            log.info("Deleting book {} from the database...", bookId);
            booksEventPublisher.publishBookDeleted(book.get());
            bookRepository.deleteById(bookId);
            return book.get();
        } else {
            throw new IllegalArgumentException(new IllegalArgumentException(String.format("Book has not been found in the database. BookId: %s", bookId)));
        }
    }

    @Transactional
    public Book updateBookById(UUID bookId, Book book) {
        Optional<Book> existingBook = bookRepository.findById(bookId);

        if (existingBook.isPresent()) {
            Book updatedBook = existingBook.get();

            updatedBook.setId(book.getId());
            updatedBook.setSubject(book.getSubject());
            updatedBook.setLink(book.getLink());
            updatedBook.setAuthor(book.getAuthor());
            updatedBook.setTitle(book.getTitle());
            log.info("Updating book [bookId={}] to [bookId={}]", bookId, existingBook.get());

            bookRepository.save(updatedBook);
            return updatedBook;

        } else {
            throw new IllegalArgumentException(new IllegalArgumentException(String.format("Book has not been found in the database. BookId: %s", bookId)));
        }
    }

    @Transactional
    public Book patchBookById(UUID bookId, Map<Object, Object> field) {
        Optional<Book> existingBook = bookRepository.findById(bookId);

        if (existingBook.isPresent()) {
            field.forEach((key, value) -> {
                Field targetField = ReflectionUtils.findField(Book.class, (String) key);
                if (targetField != null) {
                    log.info("Book entity field found -> {}", targetField.getName());
                    targetField.setAccessible(true);
                    ReflectionUtils.setField(targetField, existingBook.get(), value);
                }
            });
            Book updatedBook = existingBook.get();
            log.info("Updating [bookId={}] to [bookId={}]", bookId, existingBook.get());
            bookRepository.save(updatedBook);
            return updatedBook;
        } else {
            throw new IllegalArgumentException(String.format("Book has not been found in the database. BookId: %s", bookId));
        }
    }

    @Transactional
    public Book saveInRepository(Book book) {
        if (bookRepository.findById(book.getId()).isEmpty()) {
            bookRepository.save(book);
            booksEventPublisher.publishBookAdded(book);
            log.info("Saving [book={}] book to the books repository.", book.getId());
            return book;
        } else {
            throw new IllegalArgumentException(String.format("Book has already been saved in the database! bookId: %s", book.getId()));
        }
    }

    public Book findBookById(UUID bookId) {
        return bookRepository.findAll().stream().filter(
                book -> book.getId().equals(bookId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException
                        (String.format("Book has not been found in the database. BookId: %s", bookId)));
    }
}
