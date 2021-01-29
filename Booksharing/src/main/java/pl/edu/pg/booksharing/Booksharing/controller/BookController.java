package pl.edu.pg.booksharing.Booksharing.controller;

import com.sipios.springsearch.anotation.SearchSpec;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.pg.booksharing.Booksharing.exception.BookAlreadyExistsException;
import pl.edu.pg.booksharing.Booksharing.exception.ResourceNotFoundException;
import pl.edu.pg.booksharing.Booksharing.model.Book;
import pl.edu.pg.booksharing.Booksharing.model.DTO.BasicInfo.BookBasicInfoDto;
import pl.edu.pg.booksharing.Booksharing.model.DTO.SearchBook.BookSearchDto;
import pl.edu.pg.booksharing.Booksharing.model.DTO.SharepointBooks.BookSharepointDto;
import pl.edu.pg.booksharing.Booksharing.repository.BookRepository;
import pl.edu.pg.booksharing.Booksharing.service.BookService;

import javax.transaction.Transactional;
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

    @Autowired
    BookRepository bookRepository;

    public BookController() {
    }

    // add new book
    @PostMapping(path = "/api/book")
    public void addBookDto(@Valid @RequestBody BookBasicInfoDto bookBasicInfoDto) throws BookAlreadyExistsException {
        Book book = bookService.convertToEntity(bookBasicInfoDto);
        bookService.save(book);
//        Book bookAdded = bookService.save(book);
//        return bookService.convertToDto(bookAdded);
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

    @GetMapping(path = "/api/sharepoint/books/{email}")
    public List<BookSharepointDto> getBooksFromSharepoint(@PathVariable String email) throws ResourceNotFoundException {
      List<Book> books = bookService.findByOwnerEmail(email);

        return books.stream().map(book -> modelMapper.map(book, BookSharepointDto.class)).collect(Collectors.toList());
    }

    @GetMapping(path = "/api")
    public ResponseEntity<List<BookSearchDto>> searchForBooks(@SearchSpec Specification<Book> specs) {
        List<Book> books = bookRepository.findAll(Specification.where(specs));

        List<BookSearchDto> bookSearchDtos = books.stream().map(book -> bookService.convertSearchToDto(book)).collect(Collectors.toList());

        return new ResponseEntity<>(bookSearchDtos, HttpStatus.OK);
    }

    @Transactional
    @DeleteMapping(path = "/api/book/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBookById(id);
    }

}
