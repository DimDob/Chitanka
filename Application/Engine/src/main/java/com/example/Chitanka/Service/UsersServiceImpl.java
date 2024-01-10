package com.example.Chitanka.Service;

import com.example.Chitanka.Entity.User;
import com.example.Chitanka.Repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.UUID;

@Service
public class UsersServiceImpl implements UsersService {


    @Autowired
    private UsersRepository usersRepository;

    @Override
    public User save(User user) {
        System.out.printf("User %s saved to users database!%n", user.getUsername());
        usersRepository.save(user);
        return user;
    }


    public Optional<User> read(UUID userId) {
        Optional<User> user =  usersRepository.findById(userId);

        if (user.isPresent()) {
            return user;
        }
        System.out.println("User with the current ID is not found!");
        return Optional.empty();
    }

    @Override
    public Optional<User> update(UUID userId, User updatedUser) {
        Optional<User> existingUser = usersRepository.findById(userId);

        if (existingUser.isPresent()) {
            User userToUpdate = existingUser.get();

            userToUpdate.setUsername(updatedUser.getUsername());
            userToUpdate.setPassword(updatedUser.getPassword());

            usersRepository.save(userToUpdate);
            System.out.printf("User %s updated in users database!%n", userToUpdate.getUsername());
            return Optional.of(userToUpdate);
        } else {
            System.out.println("User with the current ID is not found!");
            return Optional.empty();
        }
    }


    @Override
    public Optional<User> delete(UUID userId) {
        Optional<User> user = usersRepository.findById(userId);

        if (user.isPresent()) {
            usersRepository.deleteById(userId);
            System.out.printf("User %s deleted from users database!%n", user.get().getUsername());
            return user;
        } else {
            System.out.println("User with ID " + userId + " not found!");
            return Optional.empty();
        }
    }

}
