package com.example.Chitanka.Controller;


import com.example.Chitanka.Entity.User;
import com.example.Chitanka.Service.UsersServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = {"*"}, maxAge = 4800, allowCredentials = "false")
public class UsersController {

    private final UsersServiceImpl usersService;

    public UsersController(UsersServiceImpl usersService) {
        this.usersService = usersService;
    }

    @PostMapping("/users")
    private User saveUser(@RequestBody User user) {
        return usersService.save(user);
    }

}
