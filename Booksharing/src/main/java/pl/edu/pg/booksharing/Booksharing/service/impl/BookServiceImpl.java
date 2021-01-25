package pl.edu.pg.booksharing.Booksharing.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pg.booksharing.Booksharing.exception.BookAlreadyExistsException;
import pl.edu.pg.booksharing.Booksharing.exception.ResourceNotFoundException;
import pl.edu.pg.booksharing.Booksharing.model.Book;
import pl.edu.pg.booksharing.Booksharing.model.DTO.BookDto;
import pl.edu.pg.booksharing.Booksharing.repository.BookRepository;
import pl.edu.pg.booksharing.Booksharing.service.BookService;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book save(Book book) throws BookAlreadyExistsException /*throws BookAlreadyExistsException*/ {
        if (bookRepository.findByIsbn(book.getIsbn()) != null) {
            throw new BookAlreadyExistsException("Book with ISBN: " + book.getIsbn() + " already exists");
        } else {
        bookRepository.save(book);
        }
        return book;
    }



    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book findById(long id) throws ResourceNotFoundException {
        Book book = bookRepository.findById(id);
        if (book == null) {
            throw new ResourceNotFoundException("Book: " + id + " not found.");
        } else {
            return book;
        }
    }

    @Override
    public Book findByIsbn(String isbn) throws ResourceNotFoundException {
        Book book = bookRepository.findByIsbn(isbn);
        if (book == null) {
            throw new ResourceNotFoundException("Book with ISBN: " + isbn + " not found.");
        } else {
            return book;
        }
    }

    @Override
    public Book convertToEntity(BookDto bookDto) {
        Book book = modelMapper.map(bookDto, Book.class);

        return book;
    }

    @Override
    public BookDto convertToDto(Book book) {
        BookDto bookDto = modelMapper.map(book, BookDto.class);

        return bookDto;
    }
}
