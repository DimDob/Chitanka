package com.example.RabbitMQ.Consumer;

public enum EmailTemplate {
    BOOK_ADDED("A new book has been added to the library: "),
    BOOK_LOANED("The book loan status has been changed: "),
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
