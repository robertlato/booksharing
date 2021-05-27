package pl.edu.pg.booksharing.Booksharing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.edu.pg.booksharing.Booksharing.exception.ResourceNotFoundException;
import pl.edu.pg.booksharing.Booksharing.model.Book;
import pl.edu.pg.booksharing.Booksharing.model.BookRating;
import pl.edu.pg.booksharing.Booksharing.model.DTO.ReviewAndRating.BookRatingDto;
import pl.edu.pg.booksharing.Booksharing.service.BookRatingService;
import pl.edu.pg.booksharing.Booksharing.service.BookService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins={ "http://localhost:8889", "http://localhost:3000" }, maxAge = 3600, allowedHeaders = "*")
@RestController
public class BookRatingController {

    private BookRatingService bookRatingService;
    private BookService bookService;

    @Autowired
    public BookRatingController(BookRatingService bookRatingService, BookService bookService) {
        this.bookRatingService = bookRatingService;
        this.bookService = bookService;
    }

    // add rating
    @PostMapping(path = "api/rating/add")
    public void addBookRating(@Valid @RequestBody BookRatingDto bookRatingDto) throws ResourceNotFoundException {
        BookRating bookRating = bookRatingService.convertToEntity(bookRatingDto);
        bookRatingService.addRating(bookRating);
    }

    //add average rating for book by id
    @GetMapping(path = "api/rating/avg/{id}")
    public String getAverageRating(@PathVariable long id) throws ResourceNotFoundException {
        double avg = bookRatingService.getAverageRating(id);
        return "{ \"avg\": \"" + avg + "\" }";
    }

    @GetMapping(path = "api/rating/{id}")
    public List<BookRatingDto> getRatings(@PathVariable long id) throws ResourceNotFoundException {
        List<BookRatingDto> ratingList = new ArrayList<>();
        List<BookRating> ratings = bookService.findById(id).getBookRatings();
        for ( BookRating rating:
             ratings) {
            BookRatingDto ratingDto = bookRatingService.convertToDto(rating);
            ratingList.add(ratingDto);
        }
        return ratingList;
    }
}
