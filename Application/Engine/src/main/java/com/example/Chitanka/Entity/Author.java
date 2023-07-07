package com.example.Chitanka.Entity;

import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Embeddable
@NoArgsConstructor
public class Author {

    private String name;

    private String birth_year;

    private String death_year;

}