package pl.edu.pg.booksharing.Booksharing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.edu.pg.booksharing.Booksharing.exception.EmailAlreadyTakenException;
import pl.edu.pg.booksharing.Booksharing.exception.ResourceNotFoundException;
import pl.edu.pg.booksharing.Booksharing.model.Address;
import pl.edu.pg.booksharing.Booksharing.model.DTO.UsersAccountSettings.UserSettingsDto;
import pl.edu.pg.booksharing.Booksharing.model.User;
import pl.edu.pg.booksharing.Booksharing.service.UserService;

import javax.validation.Valid;

@CrossOrigin(origins={ "http://localhost:8889", "http://localhost:3000" }, maxAge = 3600, allowedHeaders = "*")
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
    public User getUserById(@PathVariable long id) throws ResourceNotFoundException {
        return userService.findById(id);
    }

    @GetMapping(path = "/api/user/mail/{email}")
    public User getUserByMail(@PathVariable String email) throws ResourceNotFoundException {
        return userService.findByEmail(email);
    }

    @GetMapping(path = "/api/user/settingsdata")
    public UserSettingsDto getUserDataForSettings() {
        return userService.getUserForSettings();
    }

    @PatchMapping(path = "/api/user/update/{ownerEmail}")
    public void updateUser(@PathVariable String ownerEmail, @RequestBody User user) {
        userService.update(ownerEmail, user);
    }

    @PatchMapping(path = "/api/user/update/password")
    public void updateUsersPassword(@RequestBody String password) {
        userService.updatePassword(password);
    }

    @DeleteMapping(path = "/api/user")
    public void deleteUser() {
        userService.delete();
    }
}

