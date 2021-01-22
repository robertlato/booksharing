package pl.edu.pg.booksharing.Booksharing.service;

import pl.edu.pg.booksharing.Booksharing.exception.ResourceNotFoundException;
import pl.edu.pg.booksharing.Booksharing.model.Author;

import java.util.List;

public interface AuthorService {

    void save(Author author);

    List<Author> findAll();

    void findByLastName(String lastName) throws ResourceNotFoundException;
}
