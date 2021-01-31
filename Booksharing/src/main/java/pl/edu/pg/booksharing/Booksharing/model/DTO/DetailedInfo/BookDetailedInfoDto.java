package pl.edu.pg.booksharing.Booksharing.model.DTO.DetailedInfo;

import pl.edu.pg.booksharing.Booksharing.model.BookRating;
import pl.edu.pg.booksharing.Booksharing.model.DTO.SearchBook.AuthorSearchDto;
import pl.edu.pg.booksharing.Booksharing.model.DTO.SearchBook.PublisherSearchDto;
import pl.edu.pg.booksharing.Booksharing.model.DTO.SearchBook.SharePointSearchDto;
import pl.edu.pg.booksharing.Booksharing.model.Description;
import pl.edu.pg.booksharing.Booksharing.model.Genre;
import pl.edu.pg.booksharing.Booksharing.model.Review;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class BookDetailedInfoDto {

    private Long id;

    private String title;

    private String isbn;

    private PublisherSearchDto publisher;

    private List<AuthorSearchDto> authors = new ArrayList<>();

    private SharePointSearchDto sharePoint;

    private boolean isBorrowed;

    private Genre genre;

    private java.sql.Date releaseDate;

    private Description description;

    private List<BookRating> bookRatings = new ArrayList<>();

    private List<Review> reviews = new ArrayList<>();


    public BookDetailedInfoDto() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public PublisherSearchDto getPublisher() {
        return publisher;
    }

    public void setPublisher(PublisherSearchDto publisher) {
        this.publisher = publisher;
    }

    public List<AuthorSearchDto> getAuthors() {
        return authors;
    }

    public void setAuthors(List<AuthorSearchDto> authors) {
        this.authors = authors;
    }

    public SharePointSearchDto getSharePoint() {
        return sharePoint;
    }

    public void setSharePoint(SharePointSearchDto sharePoint) {
        this.sharePoint = sharePoint;
    }

    public boolean isBorrowed() {
        return isBorrowed;
    }

    public void setBorrowed(boolean borrowed) {
        isBorrowed = borrowed;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Description getDescription() {
        return description;
    }

    public void setDescription(Description description) {
        this.description = description;
    }

    public List<BookRating> getBookRatings() {
        return bookRatings;
    }

    public void setBookRatings(List<BookRating> bookRatings) {
        this.bookRatings = bookRatings;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }
}
