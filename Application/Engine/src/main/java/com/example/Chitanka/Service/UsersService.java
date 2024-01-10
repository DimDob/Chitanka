package com.example.Chitanka.Service;

import com.example.Chitanka.Entity.User;

import java.util.Optional;
import java.util.UUID;

public interface UsersService {

    User save(User user);

    Optional<User> read(UUID userId);

    Optional<User> update(UUID userId, User updatedUser);

    Optional<User> delete (UUID userId);
}
