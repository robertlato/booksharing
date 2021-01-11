package pl.edu.pg.booksharing.Booksharing.service;

import pl.edu.pg.booksharing.Booksharing.exception.ResourceNotFoundException;
import pl.edu.pg.booksharing.Booksharing.model.Book;

import java.util.List;

public interface BookService {

    void save(Book book);

    List<Book> findAll();

    Book findById(long id) throws ResourceNotFoundException;
}
