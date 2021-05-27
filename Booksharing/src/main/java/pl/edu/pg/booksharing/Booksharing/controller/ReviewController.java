package pl.edu.pg.booksharing.Booksharing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.edu.pg.booksharing.Booksharing.exception.ResourceNotFoundException;
import pl.edu.pg.booksharing.Booksharing.model.DTO.ReviewAndRating.ReviewAddingDto;
import pl.edu.pg.booksharing.Booksharing.model.Review;
import pl.edu.pg.booksharing.Booksharing.service.ReviewService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins={ "http://localhost:8889", "http://localhost:3000" }, maxAge = 3600, allowedHeaders = "*")
@RestController
public class ReviewController {

    private ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    //add review
    @PostMapping(path = "api/review/add")
    public void addBookReview(@Valid @RequestBody ReviewAddingDto reviewAddingDto) throws ResourceNotFoundException {
        Review review = reviewService.convertToEntity(reviewAddingDto);
        reviewService.addReview(review);
    }

    @GetMapping(path = "api/review/{id}")
    public List<ReviewAddingDto> getReviews(@PathVariable long id) throws ResourceNotFoundException {
        List<ReviewAddingDto> reviewsList = new ArrayList<>();
        List<Review> reviews = reviewService.allReviewsForBook(id);

        for (Review review:
             reviews) {
            ReviewAddingDto reviewDto = reviewService.convertToDto(review);
            reviewsList.add(reviewDto);

        }
        return reviewsList;
    }

}
