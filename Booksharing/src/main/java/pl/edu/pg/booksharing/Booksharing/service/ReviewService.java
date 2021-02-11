package pl.edu.pg.booksharing.Booksharing.service;

import pl.edu.pg.booksharing.Booksharing.exception.ResourceNotFoundException;
import pl.edu.pg.booksharing.Booksharing.model.DTO.ReviewAndRating.ReviewAddingDto;
import pl.edu.pg.booksharing.Booksharing.model.Review;

public interface ReviewService {
    Review convertToEntity(ReviewAddingDto reviewAddingDto) throws ResourceNotFoundException;

    void addReview(Review review) throws ResourceNotFoundException;
}
