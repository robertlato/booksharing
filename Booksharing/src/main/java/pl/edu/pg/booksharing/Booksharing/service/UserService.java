package pl.edu.pg.booksharing.Booksharing.service;


import pl.edu.pg.booksharing.Booksharing.exception.EmailAlreadyTakenException;
import pl.edu.pg.booksharing.Booksharing.model.User;

public interface UserService {

    void save(User user) throws EmailAlreadyTakenException;

    User findByEmail(String email);

    User findById(long id);
}

