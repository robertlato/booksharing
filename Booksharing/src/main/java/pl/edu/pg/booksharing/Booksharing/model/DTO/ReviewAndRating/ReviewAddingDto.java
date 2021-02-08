package pl.edu.pg.booksharing.Booksharing.model.DTO.ReviewAndRating;

import pl.edu.pg.booksharing.Booksharing.model.Book;

import java.sql.Timestamp;

public class ReviewAddingDto {

    private String review;

    private java.sql.Timestamp creationDate;

    private BookRnRDto book;

    public ReviewAddingDto() {
    }

    public ReviewAddingDto(String review, Timestamp creationDate, BookRnRDto book) {
        this.review = review;
        this.creationDate = creationDate;
        this.book = book;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public Timestamp getCreationDate() {
        this.creationDate = new Timestamp(System.currentTimeMillis());

        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = new Timestamp(System.currentTimeMillis());
    }

    public BookRnRDto getBook() {
        return book;
    }

    public void setBook(BookRnRDto book) {
        this.book = book;
    }
}
