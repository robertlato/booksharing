package pl.edu.pg.booksharing.Booksharing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.edu.pg.booksharing.Booksharing.exception.EmailAlreadyTakenException;
import pl.edu.pg.booksharing.Booksharing.model.User;
import pl.edu.pg.booksharing.Booksharing.repository.UserRepository;
import pl.edu.pg.booksharing.Booksharing.service.UserService;

import javax.validation.Valid;

@RestController
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // register a user
    @PostMapping(path = "/api/register")
    public void addUser(@Valid @RequestBody User user) throws EmailAlreadyTakenException {
        userService.save(user);
    }

    @GetMapping(path = "/api/user/{id}")
    public User getUser(@PathVariable long id) {
        return userService.findById(id);
    }
}

