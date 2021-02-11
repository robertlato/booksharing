package pl.edu.pg.booksharing.Booksharing.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Repository;
import pl.edu.pg.booksharing.Booksharing.component.AuthenticationFacadeImpl;
import pl.edu.pg.booksharing.Booksharing.exception.ResourceNotFoundException;
import pl.edu.pg.booksharing.Booksharing.model.Book;
import pl.edu.pg.booksharing.Booksharing.model.DTO.ReviewAndRating.ReviewAddingDto;
import pl.edu.pg.booksharing.Booksharing.model.Review;
import pl.edu.pg.booksharing.Booksharing.model.User;
import pl.edu.pg.booksharing.Booksharing.repository.ReviewRepository;
import pl.edu.pg.booksharing.Booksharing.repository.UserRepository;
import pl.edu.pg.booksharing.Booksharing.service.BookService;
import pl.edu.pg.booksharing.Booksharing.service.ReviewService;

@Repository
public class ReviewServiceImpl implements ReviewService {

    private ReviewRepository reviewRepository;
    private AuthenticationFacadeImpl authenticationFacade;
    private UserRepository userRepository;
    private BookService bookService;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository, AuthenticationFacadeImpl authenticationFacade,
                             UserRepository userRepository, BookService bookService) {
        this.reviewRepository = reviewRepository;
        this.authenticationFacade = authenticationFacade;
        this.userRepository = userRepository;
        this.bookService = bookService;
    }

    @Override
    public Review convertToEntity(ReviewAddingDto reviewAddingDto) throws ResourceNotFoundException {
        Authentication authentication = authenticationFacade.getAuthentication();
        User user = userRepository.findByEmail(authentication.getName());
        Book book = bookService.findById(reviewAddingDto.getBook().getId());

        Review review = new Review(
                reviewAddingDto.getReview(),
                reviewAddingDto.getCreationDate(),
                book,
                user
        );

        return review;

    }

    @Override
    public void addReview(Review review) throws ResourceNotFoundException {
        Book book = bookService.findById(review.getBook().getId());
        if (book.getId() == null) {
            throw new ResourceNotFoundException("You are trying to add review to non existing book");
        } else {
            reviewRepository.save(review);
        }
    }
}
