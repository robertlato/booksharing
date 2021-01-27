package pl.edu.pg.booksharing.Booksharing.service;

import pl.edu.pg.booksharing.Booksharing.exception.BookAlreadyExistsException;
import pl.edu.pg.booksharing.Booksharing.exception.ResourceNotFoundException;
import pl.edu.pg.booksharing.Booksharing.model.Book;
import pl.edu.pg.booksharing.Booksharing.model.DTO.BasicInfo.BookBasicInfoDto;

import java.util.List;

public interface BookService {

    Book save(Book book);

    List<Book> findAll();

    Book findById(long id) throws ResourceNotFoundException;

    Book findByIsbn(String isbn) throws ResourceNotFoundException;

    BookBasicInfoDto convertToDto(Book book);
    Book convertToEntity(BookBasicInfoDto bookBasicInfoDto);
}
