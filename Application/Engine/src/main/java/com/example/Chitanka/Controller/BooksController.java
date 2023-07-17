package com.example.Chitanka.Controller;

import com.example.Chitanka.Entity.Book;
import com.example.Chitanka.Service.BookService;
import com.example.Chitanka.Service.AllBooksDataService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;
import java.util.UUID;


@RestController
@RequestMapping
@CrossOrigin(origins = {"*"}, maxAge = 4800, allowCredentials = "false")
public class BooksController {

    private final BookService bookService;
    private final AllBooksDataService bookUrls;
    public final String API = "https://gutendex.com/";

    public BooksController(BookService bookService, AllBooksDataService bookUrls) {
        this.bookService = bookService;
        this.bookUrls = bookUrls;
    }

    @GetMapping(value = "/books")
    public Page<Book> requestBooksFromAPI(Pageable pageable) throws IOException {
        return bookUrls.booksJson(API +"books", pageable);
    }

    @GetMapping(value = "/books/{bookId}")
    public Book getBook(@PathVariable UUID bookId) {
        return bookService.find(bookId);
    }

    @PostMapping(value = "/books")
    public Book saveBook(@RequestBody Book book) {
        return bookService.save(book);
    }

    @PutMapping(value = "/books/{bookId}")
    public Book updateBook(@PathVariable UUID bookId, @RequestBody Book book) throws SQLException {
        return bookService.update(bookId, book);
    }

    @GetMapping(value = "books/{bookId}/loan")
    public boolean isBookLoaned(@PathVariable UUID bookId) {
        return bookService.isLoaned(bookId);
    }

    @PutMapping(value = "books/{bookId}/loan")
    public Book loanBook(@PathVariable UUID bookId) {
        return bookService.loan(bookId);
    }

    @DeleteMapping(value = "/books/{bookId}")
    public Book deleteBook(@PathVariable UUID bookId) {
        return bookService.delete(bookId);
    }

    @PatchMapping(value = "/books/{bookId}")
    public Book patchBook(@PathVariable UUID bookId, @RequestBody Map<Object, Object> fields) {
        return bookService.patch(bookId, fields);
    }

}
