package com.example.RabbitMQ.Consumer;

import schemas.messaging.avro.events.books.loaned.BookLoaned;

public class GenerateContentMessages {


    public static String bookLoanedContentMessage(BookLoaned deserializedMessage) {
        String bookTitle = String.valueOf(deserializedMessage.get("title")).replace("\"", "");
        String bookUrl = String.valueOf(deserializedMessage.get("link")).replace("\"", "");
        String author = String.valueOf(deserializedMessage.get("author")).replaceAll("^Author\\(|\\)$", "");

        return "More info about the book: <br>"
                + "Title - " + bookTitle + "<br>"
                + "Author - " + author + "<br>"
                + "Link to the book - " + bookUrl;
    }
}
