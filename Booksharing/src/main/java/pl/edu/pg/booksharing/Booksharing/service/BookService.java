package pl.edu.pg.booksharing.Booksharing.service;

import pl.edu.pg.booksharing.Booksharing.exception.BookAlreadyExistsException;
import pl.edu.pg.booksharing.Booksharing.exception.ResourceNotFoundException;
import pl.edu.pg.booksharing.Booksharing.model.Book;
import pl.edu.pg.booksharing.Booksharing.model.DTO.BasicInfo.BookBasicInfoDto;
import pl.edu.pg.booksharing.Booksharing.model.DTO.DetailedInfo.BookDetailedInfoDto;
import pl.edu.pg.booksharing.Booksharing.model.DTO.SearchBook.BookSearchDto;

import java.util.List;

public interface BookService {

    void save(Book book);

    List<Book> findAll();

    Book findById(long id) throws ResourceNotFoundException;

    Book findByIsbn(String isbn) throws ResourceNotFoundException;

    List<Book> findByOwnerEmail(String email) throws ResourceNotFoundException;

    BookBasicInfoDto convertToDto(Book book);

    BookDetailedInfoDto convertToDetailedDto(Book book);

    BookSearchDto convertSearchToDto(Book book);

    Book convertToEntity(BookBasicInfoDto bookBasicInfoDto);

    void deleteBookById(long id);
}
