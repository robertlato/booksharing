package pl.edu.pg.booksharing.Booksharing.model.DTO.BasicInfo;

import java.sql.Timestamp;

public class ReviewForBookDto {

    private String review;

    private java.sql.Timestamp creationDate;

    public ReviewForBookDto() {
    }

    public ReviewForBookDto(String review, Timestamp creationDate) {
        this.review = review;
        this.creationDate = creationDate;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }
}
