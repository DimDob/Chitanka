package com.example.Chitanka.Service;

import com.example.Chitanka.Entity.Book;

import java.sql.SQLException;
import java.util.Map;
import java.util.UUID;

public interface BookService {

    Book save(Book book);

    Book delete(UUID bookId);

    Book find(UUID bookId);

    Book update(UUID bookId, Book book) throws SQLException;

    Book patch(UUID bookId, Map<Object, Object> field);

    boolean isLoaned(UUID bookId);

    boolean loan(UUID bookId);
}
