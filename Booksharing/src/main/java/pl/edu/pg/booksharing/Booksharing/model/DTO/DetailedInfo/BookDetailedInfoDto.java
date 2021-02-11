package pl.edu.pg.booksharing.Booksharing.model.DTO.DetailedInfo;

import pl.edu.pg.booksharing.Booksharing.model.DTO.BasicInfo.BookRatingInfoDto;
import pl.edu.pg.booksharing.Booksharing.model.DTO.BasicInfo.GenreInfoForBookDto;
import pl.edu.pg.booksharing.Booksharing.model.DTO.BasicInfo.ReviewForBookDto;
import pl.edu.pg.booksharing.Booksharing.model.DTO.SearchBook.AuthorSearchDto;
import pl.edu.pg.booksharing.Booksharing.model.DTO.SearchBook.PublisherSearchDto;
import pl.edu.pg.booksharing.Booksharing.model.DTO.SearchBook.SharePointSearchDto;
import pl.edu.pg.booksharing.Booksharing.model.Description;

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

    private GenreInfoForBookDto genre;

    private java.sql.Date releaseDate;

    private Description description;

    private List<BookRatingInfoDto> bookRatings = new ArrayList<>();

    private List<ReviewForBookDto> reviews = new ArrayList<>();


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

    public GenreInfoForBookDto getGenre() {
        return genre;
    }

    public void setGenre(GenreInfoForBookDto genre) {
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

    public List<BookRatingInfoDto> getBookRatings() {
        return bookRatings;
    }

    public void setBookRatings(List<BookRatingInfoDto> bookRatings) {
        this.bookRatings = bookRatings;
    }

    public List<ReviewForBookDto> getReviews() {
        return reviews;
    }

    public void setReviews(List<ReviewForBookDto> reviews) {
        this.reviews = reviews;
    }
}
