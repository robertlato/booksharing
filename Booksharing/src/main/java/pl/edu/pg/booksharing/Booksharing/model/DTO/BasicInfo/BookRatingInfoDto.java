package pl.edu.pg.booksharing.Booksharing.model.DTO.BasicInfo;

public class BookRatingInfoDto {

    private int rating;

    public BookRatingInfoDto() {
    }

    public BookRatingInfoDto(int rating) {
        this.rating = rating;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
