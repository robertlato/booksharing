package pl.edu.pg.booksharing.Booksharing.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import pl.edu.pg.booksharing.Booksharing.component.AuthenticationFacadeImpl;
import pl.edu.pg.booksharing.Booksharing.exception.BookAlreadyExistsException;
import pl.edu.pg.booksharing.Booksharing.exception.ResourceNotFoundException;
import pl.edu.pg.booksharing.Booksharing.model.*;
import pl.edu.pg.booksharing.Booksharing.model.DTO.BasicInfo.AuthorInfoForBookDto;
import pl.edu.pg.booksharing.Booksharing.model.DTO.BasicInfo.BookBasicInfoDto;
import pl.edu.pg.booksharing.Booksharing.model.DTO.DetailedInfo.BookDetailedInfoDto;
import pl.edu.pg.booksharing.Booksharing.model.DTO.SearchBook.BookSearchDto;
import pl.edu.pg.booksharing.Booksharing.repository.*;
import pl.edu.pg.booksharing.Booksharing.service.BookService;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;
    private UserRepository userRepository;
    private PublisherRepository publisherRepository;
    private AuthorRepository authorRepository;
    private SharePointRepository sharePointRepository;
    private AuthenticationFacadeImpl authenticationFacade;
    @Autowired
    ModelMapper modelMapper;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, UserRepository userRepository,
                           PublisherRepository publisherRepository, AuthorRepository authorRepository,
                           SharePointRepository sharePointRepository, AuthenticationFacadeImpl authenticationFacade) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
        this.publisherRepository = publisherRepository;
        this.authorRepository = authorRepository;
        this.sharePointRepository = sharePointRepository;
        this.authenticationFacade = authenticationFacade;
    }

    @Override
    public void save(Book book){
        bookRepository.save(book);
    }



    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public List<Book> searchEngineAll(Specification<Book> specs) {
        Authentication authentication = authenticationFacade.getAuthentication();
        User user = userRepository.findByEmail(authentication.getName());
        List<Book> allBooks = bookRepository.findAll(Specification.where(specs));
        List<Book> searchedBooks = new ArrayList<>();

        if (user == null) {
            return allBooks;
        } else {
            for (Book book:
                    allBooks) {

                if (book.getSharePoint().getUser().getId() == user.getId()) {

                } else {
                    searchedBooks.add(book);
                }

            }
        }

        return searchedBooks;
    }

    @Override
    public List<Book> searchEngineByAuthor(Specification<Author> specs) {
        Authentication authentication = authenticationFacade.getAuthentication();
        User user = userRepository.findByEmail(authentication.getName());
        List<Author> authors = authorRepository.findAll(Specification.where(specs));
        List<Book> books = new ArrayList<>();
        for (Author author:
                authors) {
            List<Book> booksAuthor = author.getBooks();
            for (Book bookList:
                    booksAuthor) {
                if (user == null) {
                    books.add(bookList);
                } else {
                    if (bookList.getSharePoint().getUser().getId() == user.getId()) {

                    } else {
                        books.add(bookList);
                    }
                }

            }
        }
        return books;

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
    public List<Book> findByIsbn(String isbn) throws ResourceNotFoundException {
        List<Book> books = bookRepository.findAll();
        List<Book> booksIsbnList = new ArrayList<>();
        for (Book book:
             books) {
            if (book.getIsbn().equals(isbn)) {
                booksIsbnList.add(book);
            }
        }
        if (booksIsbnList == null) {
            throw new ResourceNotFoundException("Book with ISBN: " + isbn + " not found.");
        } else {
            return booksIsbnList;
        }
    }

    @Override
    public List<Book> findByOwnerEmail(String email) throws ResourceNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new ResourceNotFoundException("User " + email + "do not exists");
        } else {
            SharePoint sharePoint = user.getSharePoints().get(0);
            List<Book> books = sharePoint.getBooks();

            return books;
        }
    }

    @Override
    public Book convertToEntity(BookBasicInfoDto bookBasicInfoDto) {
//        Book book = modelMapper.map(bookBasicInfoDto, Book.class);
        User user = userRepository.findByEmail(bookBasicInfoDto.getSharePoint().getUser().getEmail());
        Publisher publisher = publisherRepository.findByName(bookBasicInfoDto.getPublisher().getName());


        if (publisher == null) {
            publisher = new Publisher();
            publisher.setName(bookBasicInfoDto.getPublisher().getName());
            publisher.setAddress(new Address());
        }

        List<Author> authors = new ArrayList<>();
        for (AuthorInfoForBookDto myAuthor : bookBasicInfoDto.getAuthors()) {
            Author author = authorRepository.findByFirstNameAndLastName(myAuthor.getFirstName(), myAuthor.getLastName());
            if (author == null) {
                author = new Author();
                author.setFirstName(myAuthor.getFirstName());
                author.setLastName(myAuthor.getLastName());
            }
            authors.add(author);
        }


        Book book = new Book(
                bookBasicInfoDto.getReleaseDate(),
                bookBasicInfoDto.getTitle(),
                bookBasicInfoDto.getIsbn(),
                publisher,
                authors,
                bookBasicInfoDto.getGenre(),
                user.getSharePoints().get(0)
        );
        return book;
    }

    @Override
    public BookBasicInfoDto convertToDto(Book book) {
        BookBasicInfoDto bookBasicInfoDto = modelMapper.map(book, BookBasicInfoDto.class);

        return bookBasicInfoDto;
    }

    @Override
    public BookDetailedInfoDto convertToDetailedDto(Book book) {
        BookDetailedInfoDto bookDetailedInfoDto = modelMapper.map(book, BookDetailedInfoDto.class);

        return bookDetailedInfoDto;
    }

    @Override
    public BookSearchDto convertSearchToDto(Book book) {
        BookSearchDto bookSearchDto = modelMapper.map(book, BookSearchDto.class);

        return bookSearchDto;
    }

    @Transactional
    @Override
    public void deleteBookById(long id) {
        bookRepository.deleteById(id);
    }

}
