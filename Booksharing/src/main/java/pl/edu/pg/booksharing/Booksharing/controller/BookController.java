package pl.edu.pg.booksharing.Booksharing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.edu.pg.booksharing.Booksharing.exception.BookAlreadyExistsException;
import pl.edu.pg.booksharing.Booksharing.exception.ResourceNotFoundException;
import pl.edu.pg.booksharing.Booksharing.model.Book;
import pl.edu.pg.booksharing.Booksharing.service.BookService;

import javax.validation.Valid;
import java.util.List;

@RestController
public class BookController {

    //    private BookService bookService;
//
//    @Autowired
//    public BookController(BookService bookService) { this.bookService = bookService; }

    @Autowired
    BookService bookService;

    public BookController() {
    }

    // add new book
    @PostMapping(path = "/api/book")
    public void addBook(@Valid @RequestBody Book book) throws BookAlreadyExistsException {
        bookService.save(book);
    }

    // get all books
    @GetMapping(path = "api/books")
    public List<Book> getAllBooks(){
        return bookService.findAll();
    }

    // get book by id
    @GetMapping(path = "/api/book/{id}")
    public Book getBookByID(@PathVariable long id) throws ResourceNotFoundException {
        return bookService.findById(id);
    }


}
