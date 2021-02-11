package pl.edu.pg.booksharing.Booksharing.model.DTO.ReviewAndRating;

public class BookRatingDto {

    private int rating;

    private BookRnRDto book;

    public BookRatingDto() {
    }

    public BookRatingDto(int rating, BookRnRDto book) {
        this.rating = rating;
        this.book = book;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public BookRnRDto getBook() {
        return book;
    }

    public void setBook(BookRnRDto book) {
        this.book = book;
    }
}
