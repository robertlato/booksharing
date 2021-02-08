package pl.edu.pg.booksharing.Booksharing.controller;

import com.sipios.springsearch.anotation.SearchSpec;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.pg.booksharing.Booksharing.exception.ResourceNotFoundException;
import pl.edu.pg.booksharing.Booksharing.model.Author;
import pl.edu.pg.booksharing.Booksharing.model.Book;
import pl.edu.pg.booksharing.Booksharing.model.DTO.BasicInfo.BookBasicInfoDto;
import pl.edu.pg.booksharing.Booksharing.model.DTO.DetailedInfo.BookDetailedInfoDto;
import pl.edu.pg.booksharing.Booksharing.model.DTO.SearchBook.BookSearchDto;
import pl.edu.pg.booksharing.Booksharing.model.DTO.SharepointBooks.BookSharepointDto;
import pl.edu.pg.booksharing.Booksharing.repository.AuthorRepository;
import pl.edu.pg.booksharing.Booksharing.repository.BookRepository;
import pl.edu.pg.booksharing.Booksharing.service.BookService;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins={ "http://localhost:8889", "http://localhost:3000" }, maxAge = 3600, allowedHeaders = "*")
@RestController
public class BookController {

    BookService bookService;
    ModelMapper modelMapper;
    BookRepository bookRepository;
    AuthorRepository authorRepository;

    public BookController() {
    }

    @Autowired
    public BookController(BookService bookService, ModelMapper modelMapper,
                          BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookService = bookService;
        this.modelMapper = modelMapper;
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    // add new book
    @PostMapping(path = "/api/book")
    public void addBookDto(@Valid @RequestBody BookBasicInfoDto bookBasicInfoDto) {
        Book book = bookService.convertToEntity(bookBasicInfoDto);
        bookService.save(book);
    }

    // get all books
   @GetMapping(path = "api/books")
   public List<BookBasicInfoDto> getAllBooks(){
        List<Book> books = bookService.findAll();

        return books.stream().map(book -> modelMapper.map(book, BookBasicInfoDto.class)).collect(Collectors.toList());
    }


    // get book by id
    @GetMapping(path = "/api/book/{id}")
    public BookDetailedInfoDto getBookByID(@PathVariable long id) throws ResourceNotFoundException {
        return bookService.convertToDetailedDto(bookService.findById(id));
    }

    // get book by isbn
    @GetMapping(path = "/api/book/isbn/{isbn}")
    public List<BookBasicInfoDto> getBookByIsbn(@PathVariable String isbn) throws ResourceNotFoundException {
        List<Book> books = bookService.findByIsbn(isbn);
        List<BookBasicInfoDto> booksDto = new ArrayList<>();
        for (Book book:
             books) {
            booksDto.add(bookService.convertToDto(book));
        }
         return booksDto;
    }

    // get books by owner email
    @GetMapping(path = "/api/sharepoint/books/{email}")
    public List<BookSharepointDto> getBooksFromSharepoint(@PathVariable String email) throws ResourceNotFoundException {
      List<Book> books = bookService.findByOwnerEmail(email);

        return books.stream().map(book -> modelMapper.map(book, BookSharepointDto.class)).collect(Collectors.toList());
    }

    // search engine for books
    @GetMapping(path = "/api")
    public ResponseEntity<List<BookSearchDto>> searchForBooks(@SearchSpec Specification<Book> specs) {
        List<Book> books = bookRepository.findAll(Specification.where(specs));

        List<BookSearchDto> bookSearchDtos = books.stream().map(book -> bookService.convertSearchToDto(book)).collect(Collectors.toList());

        return new ResponseEntity<>(bookSearchDtos, HttpStatus.OK);
    }

    // search engine for books
    @GetMapping(path = "/api/books/authors")
    public ResponseEntity<List<BookSearchDto>> searchForBooksByAuthors(@SearchSpec Specification<Author> specs) {
        List<Author> authors = authorRepository.findAll(Specification.where(specs));
        List<Book> books = new ArrayList<>();
        for (Author author:
             authors) {
            List<Book> booksAuthor = author.getBooks();
            for (Book bookList:
                 booksAuthor) {
                books.add(bookList);
            }
        }

        List<BookSearchDto> bookSearchDtos = books.stream().map(book -> bookService.convertSearchToDto(book)).collect(Collectors.toList());

        return new ResponseEntity<>(bookSearchDtos, HttpStatus.OK);
    }

    @Transactional
    @DeleteMapping(path = "/api/book/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBookById(id);
    }

}
