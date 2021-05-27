package pl.edu.pg.booksharing.Booksharing.service;

import pl.edu.pg.booksharing.Booksharing.exception.ResourceNotFoundException;
import pl.edu.pg.booksharing.Booksharing.model.BookRating;
import pl.edu.pg.booksharing.Booksharing.model.DTO.ReviewAndRating.BookRatingDto;

import java.util.List;

public interface BookRatingService {

    BookRating convertToEntity(BookRatingDto bookRatingDto) throws ResourceNotFoundException;

    BookRatingDto convertToDto(BookRating bookRating);

    void addRating(BookRating bookRating) throws ResourceNotFoundException;

    double getAverageRating(long id) throws ResourceNotFoundException;

    List<BookRating> getBookRatings(long id) throws ResourceNotFoundException;
}
