package com.example.Chitanka.Entity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;


@Data
@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String username;

    private String password;

}
