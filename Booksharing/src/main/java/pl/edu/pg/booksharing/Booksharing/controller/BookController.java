package pl.edu.pg.booksharing.Booksharing.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.edu.pg.booksharing.Booksharing.exception.BookAlreadyExistsException;
import pl.edu.pg.booksharing.Booksharing.exception.ResourceNotFoundException;
import pl.edu.pg.booksharing.Booksharing.model.Book;
import pl.edu.pg.booksharing.Booksharing.model.DTO.BasicInfo.BookBasicInfoDto;
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
    public BookBasicInfoDto addBookDto(@Valid @RequestBody BookBasicInfoDto bookBasicInfoDto) throws BookAlreadyExistsException {
        Book book = bookService.convertToEntity(bookBasicInfoDto);
        Book bookAdded = bookService.save(book);
        return bookService.convertToDto(bookAdded);
    }


    // get all books
   @GetMapping(path = "api/books")
    public List<BookBasicInfoDto> getAllBooks(){
        List<Book> books = bookService.findAll();

        return books.stream().map(book -> modelMapper.map(book, BookBasicInfoDto.class)).collect(Collectors.toList());
    }

    // get all books
 /*   @GetMapping(path = "api/books")
    public List<Book> getAllBooks(){
        return bookService.findAll();
    }*/

    // get book by id
    @GetMapping(path = "/api/book/{id}")
    public BookBasicInfoDto getBookByID(@PathVariable long id) throws ResourceNotFoundException {
        return bookService.convertToDto(bookService.findById(id));
    }

    // get book by isbn
    @GetMapping(path = "/api/book/isbn/{isbn}")
    public BookBasicInfoDto getBookByIsbn(@PathVariable String isbn) throws ResourceNotFoundException {
        return bookService.convertToDto(bookService.findByIsbn(isbn));
    }

}
