package pl.edu.pg.booksharing.Booksharing.service;

import pl.edu.pg.booksharing.Booksharing.exception.ResourceNotFoundException;
import pl.edu.pg.booksharing.Booksharing.model.DTO.ReviewAndRating.ReviewAddingDto;
import pl.edu.pg.booksharing.Booksharing.model.Review;

import java.util.List;

public interface ReviewService {
    Review convertToEntity(ReviewAddingDto reviewAddingDto) throws ResourceNotFoundException;

    void addReview(Review review) throws ResourceNotFoundException;

    List<Review> allReviewsForBook(long id) throws ResourceNotFoundException;

    ReviewAddingDto convertToDto(Review review);
}
