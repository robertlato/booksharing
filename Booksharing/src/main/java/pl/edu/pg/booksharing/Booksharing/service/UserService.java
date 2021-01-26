package pl.edu.pg.booksharing.Booksharing.service;


import pl.edu.pg.booksharing.Booksharing.exception.EmailAlreadyTakenException;
import pl.edu.pg.booksharing.Booksharing.exception.ResourceNotFoundException;
import pl.edu.pg.booksharing.Booksharing.model.User;

public interface UserService {

    void save(User user) throws EmailAlreadyTakenException;

    User findByEmail(String email) throws ResourceNotFoundException;

    User findById(long id) throws ResourceNotFoundException;
}

