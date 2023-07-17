package com.example.Chitanka.Service;

import com.example.Chitanka.Entity.Book;
import com.example.Chitanka.Service.rabbitMQMessagePublisher.BooksEventPublisher;
import com.example.Chitanka.db.interaction.SQLInteractionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    @Autowired
    private final SQLInteractionService sqlMigrationService;

    @Autowired
    private final CRUDService crudService;

    @Autowired
    private final BooksEventPublisher booksEventPublisher;



    @Override
    public Book save(Book book) {
        return crudService.saveInRepository(book);
    }

    @Override
    public Book delete(UUID bookId) {
        return crudService.deleteBookById(bookId);
    }

    @Override
    public Book find(UUID bookId) {
        return crudService.findBookById(bookId);
    }

    @Override
    public Book update(UUID bookId, Book book) throws SQLException {
        return crudService.updateBookById(bookId, book);
    }



    @Override
    public Book patch(UUID bookId, Map<Object, Object> field) {
        return crudService.patchBookById(bookId, field);
    }

    public boolean isLoaned(UUID bookId) {
        return sqlMigrationService.getBookLoanStatusById(bookId);
    }

    @Override
    public Book loan(UUID bookId) {
        Book book = find(bookId);
        booksEventPublisher.publishBookLoaned(book);
        sqlMigrationService.loanBookById(bookId);
        return book;
    }


}

