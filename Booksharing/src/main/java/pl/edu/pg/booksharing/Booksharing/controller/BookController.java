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

@CrossOrigin(origins={ "http://localhost:8889", "http://localhost:3000" }, maxAge = 3600, allowedHeaders = "*")
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
        Book book = bookService.convertToEntity(bookDto);
        Book bookAdded = bookService.save(book);
        return bookService.convertToDto(bookAdded);
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
        return bookService.convertToDto(bookService.findById(id));
    }

    // get book by isbn
    @GetMapping(path = "/api/book/isbn/{isbn}")
    public BookDto getBookByIsbn(@PathVariable String isbn) throws ResourceNotFoundException {
        return bookService.convertToDto(bookService.findByIsbn(isbn));
    }

}
