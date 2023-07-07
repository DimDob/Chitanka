package com.example.Chitanka.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Entity
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name="link", columnDefinition = "TEXT")
    private String link;

    @Column(name = "subject", columnDefinition = "TEXT")
    private String subject;

    @Embedded
    @Column(name = "author", columnDefinition = "TEXT")
    private Author author;

    @Column(name="title", columnDefinition = "TEXT")
    private String title;

}
