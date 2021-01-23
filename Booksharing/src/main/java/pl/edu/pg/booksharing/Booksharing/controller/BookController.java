package pl.edu.pg.booksharing.Booksharing.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.edu.pg.booksharing.Booksharing.exception.BookAlreadyExistsException;
import pl.edu.pg.booksharing.Booksharing.exception.ResourceNotFoundException;
import pl.edu.pg.booksharing.Booksharing.model.Book;
import pl.edu.pg.booksharing.Booksharing.model.DTO.BookDto;
import pl.edu.pg.booksharing.Booksharing.service.BookService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class BookController {

    //    private BookService bookService;
//
//    @Autowired
//    public BookController(BookService bookService) { this.bookService = bookService; }

    @Autowired
    BookService bookService;

    @Autowired
    ModelMapper modelMapper;

    public BookController() {
    }

    // add new book
    @PostMapping(path = "/api/book")
    public BookDto addBookDto(@Valid @RequestBody BookDto bookDto) throws BookAlreadyExistsException {
        Book book = convertToEntity(bookDto);
        Book bookAdded = bookService.save(book);
        return convertToDto(bookAdded);
    }

    // TODO getting all books with DTO
/*    @GetMapping(path = "api/books")
    public List<BookDto> getAllBooks(){
        List<Book> books = bookService.findAll();

        return books.stream().map(this::convertToDto).collect(Collectors.toList());
    }*/

    // get all books
    @GetMapping(path = "api/books")
    public List<Book> getAllBooks(){
        return bookService.findAll();
    }

    // get book by id
    @GetMapping(path = "/api/book/{id}")
    public BookDto getBookByID(@PathVariable long id) throws ResourceNotFoundException {
        return convertToDto(bookService.findById(id));
    }

    // get book by isbn
    @GetMapping(path = "/api/book/{isbn}")
    public BookDto getBookByIsbn(@PathVariable String isbn) throws ResourceNotFoundException {
        return convertToDto(bookService.findByIsbn(isbn));
    }

    private Book convertToEntity(BookDto bookDto) {
        Book book = modelMapper.map(bookDto, Book.class);

        return book;
    }

    private BookDto convertToDto(Book book) {
        BookDto bookDto = modelMapper.map(book, BookDto.class);

        return bookDto;
    }

}
