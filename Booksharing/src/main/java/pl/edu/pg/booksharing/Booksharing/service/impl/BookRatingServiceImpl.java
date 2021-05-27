package pl.edu.pg.booksharing.Booksharing.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Repository;
import pl.edu.pg.booksharing.Booksharing.component.AuthenticationFacadeImpl;
import pl.edu.pg.booksharing.Booksharing.exception.ResourceNotFoundException;
import pl.edu.pg.booksharing.Booksharing.model.Book;
import pl.edu.pg.booksharing.Booksharing.model.BookRating;
import pl.edu.pg.booksharing.Booksharing.model.DTO.ReviewAndRating.BookRatingDto;
import pl.edu.pg.booksharing.Booksharing.model.DTO.ReviewAndRating.BookRnRDto;
import pl.edu.pg.booksharing.Booksharing.model.User;
import pl.edu.pg.booksharing.Booksharing.repository.BookRatingRepository;
import pl.edu.pg.booksharing.Booksharing.repository.UserRepository;
import pl.edu.pg.booksharing.Booksharing.service.BookRatingService;
import pl.edu.pg.booksharing.Booksharing.service.BookService;

import java.util.List;

@Repository
public class BookRatingServiceImpl implements BookRatingService {

    private BookRatingRepository bookRatingRepository;
    private AuthenticationFacadeImpl authenticationFacade;
    private UserRepository userRepository;
    private BookService bookService;
    ModelMapper modelMapper;

    @Autowired
    public BookRatingServiceImpl(BookRatingRepository bookRatingRepository,
                                 AuthenticationFacadeImpl authenticationFacade, UserRepository userRepository,
                                 BookService bookService) {
        this.bookRatingRepository = bookRatingRepository;
        this.authenticationFacade = authenticationFacade;
        this.userRepository = userRepository;
        this.bookService = bookService;
    }

    @Override
    public BookRating convertToEntity(BookRatingDto bookRatingDto) throws ResourceNotFoundException {
        Authentication authentication = authenticationFacade.getAuthentication();
        User user = userRepository.findByEmail(authentication.getName());
        Book book = bookService.findById(bookRatingDto.getBook().getId());

        List<BookRating> bookRatingList = book.getBookRatings();

        for (BookRating br:
             bookRatingList) {
            if (br.getUser().getId() == user.getId() && br.getBook().getId() == book.getId()) {
                BookRating bookRating = br;
                bookRating.setRating(bookRatingDto.getRating());

                return bookRating;
            }
        }

        BookRating bookRating = new BookRating(
                bookRatingDto.getRating(),
                book,
                user
        );
        return bookRating;
    }

    @Override
    public void addRating(BookRating bookRating) throws ResourceNotFoundException {
        Book book = bookService.findById(bookRating.getBook().getId());

        if (book.getId() == null) {
            throw new ResourceNotFoundException("You are trying to rate non existing book");
        } else {
            bookRatingRepository.save(bookRating);
        }
    }

    @Override
    public double getAverageRating(long id) throws ResourceNotFoundException {
        Book book = bookService.findById(id);
        List<BookRating> bookRatings = book.getBookRatings();
        int allRatings = 0;
        for (BookRating br:
             bookRatings) {
            allRatings = allRatings + br.getRating();
        }

        double avg = ((double)allRatings/bookRatings.size());

        avg = avg*100;
        avg = Math.round(avg);
        avg = avg/100;

        return avg;
    }

    @Override
    public BookRatingDto convertToDto(BookRating bookRating) {
        BookRatingDto bookRatingDto = new BookRatingDto();
        bookRatingDto.setRating(bookRating.getRating());
        Book book = bookRating.getBook();
        BookRnRDto bookRnRDto = new BookRnRDto();
        bookRnRDto.setId(book.getId());
        bookRatingDto.setBook(bookRnRDto);
        return bookRatingDto;
    }

    @Override
    public List<BookRating> getBookRatings(long id) throws ResourceNotFoundException {
        Book book = bookService.findById(id);

        return book.getBookRatings();
    }
}
