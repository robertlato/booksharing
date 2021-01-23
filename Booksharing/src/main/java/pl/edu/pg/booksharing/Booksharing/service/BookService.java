package pl.edu.pg.booksharing.Booksharing.service;

import pl.edu.pg.booksharing.Booksharing.exception.BookAlreadyExistsException;
import pl.edu.pg.booksharing.Booksharing.exception.ResourceNotFoundException;
import pl.edu.pg.booksharing.Booksharing.model.Book;

import java.util.List;

public interface BookService {

    Book save(Book book) throws BookAlreadyExistsException;

    List<Book> findAll();

    Book findById(long id) throws ResourceNotFoundException;

    Book findByIsbn(String isbn) throws ResourceNotFoundException;
}
