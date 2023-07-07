package com.example.Chitanka.Service;

import com.example.Chitanka.Entity.Author;
import com.example.Chitanka.Entity.Book;
import com.example.Chitanka.Entity.Subject;
import com.example.Chitanka.Repository.BookRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Component
public class AllBooksDataService {

    private static BookServiceImpl bookServiceImpl;
    private static final ObjectMapper objectMapper = new ObjectMapper();

    private final BookRepository bookRepository;

    @Autowired
    public AllBooksDataService(BookServiceImpl bookServiceImpl, BookRepository bookRepository) {
        AllBooksDataService.bookServiceImpl = bookServiceImpl;
        this.bookRepository = bookRepository;
    }

    public Page<Book> booksJson(String API, Pageable pageable) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        String json = restTemplate.getForObject(API, String.class);
        return getBookInfo(json, pageable);
    }

    public Page<Book> getBookInfo(String json, Pageable pageable) throws JsonProcessingException {


        JsonNode jsonFromAPI = objectMapper.readTree(json);
        if (jsonFromAPI.isObject()) {
            JsonNode fields = jsonFromAPI.get("results");
            for (JsonNode key : fields) {

                String bookTitle = String.valueOf(key.get("title"));
                String authors = String.valueOf(key.get("authors"));
                JsonNode formatsNode = key.get("formats");
                String bookURL = String.valueOf(formatsNode.get("text/html"));


                JsonNode jsonArray = objectMapper.readTree(authors);
                Author author = objectMapper.readValue(jsonArray.get(0).toString(), Author.class);
                Subject[] subjects = objectMapper.readValue(String.valueOf(key.get("subjects")), Subject[].class);
                String subjectsToString = subjectsToString(subjects);
                Book book = new Book(UUID.randomUUID(), bookURL, subjectsToString, author, bookTitle);
                bookServiceImpl.save(book);
            }
        }

        return getPageable(pageable);
    }

    private Page<Book> getPageable(Pageable pageable) {
        int totalElements = (int) bookRepository.count();
        int pageSize = Integer.MAX_VALUE;
        int pageNumber = pageable.getPageNumber();

        pageable = PageRequest.of(pageNumber, pageSize);
        List<Book> books = bookRepository.findAll(pageable).getContent();
        return new PageImpl<>(books, pageable, totalElements);
    }

    private String subjectsToString(Subject[] subjects) throws JsonProcessingException {
        return objectMapper.writeValueAsString(subjects);
    }


}
