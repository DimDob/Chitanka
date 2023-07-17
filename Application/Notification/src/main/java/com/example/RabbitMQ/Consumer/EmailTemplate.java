package com.example.RabbitMQ.Consumer;

public enum EmailTemplate {
    BOOK_ADDED("A new book has been added to the library: "),
    BOOK_LOANED("Congratulations! You have loaned a new book! "),
    BOOK_DELETED("The book has been deleted: "),

    GREET("Hello, ${receiver}");


    private final String template;

    EmailTemplate(String template) {
        this.template = template;
    }

    public String getTemplate() {
        return template;
    }
}
