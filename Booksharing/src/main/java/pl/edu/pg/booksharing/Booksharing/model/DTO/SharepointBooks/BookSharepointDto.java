package pl.edu.pg.booksharing.Booksharing.model.DTO.SharepointBooks;

import pl.edu.pg.booksharing.Booksharing.model.Publisher;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class BookSharepointDto {

    private Long id;

    private String title;

    private List<AuthorSharepointDto> authors = new ArrayList<>();

    private PublisherSharepointDto publisher;

    private String isbn;

    private java.sql.Date releaseDate;

    private boolean isBorrowed;

    private String userWhoBorrowed;

    private java.sql.Timestamp borrowingDate;

    public BookSharepointDto() {
    }

    public BookSharepointDto(Long id, String title, List<AuthorSharepointDto> authors, PublisherSharepointDto publisher,
                             String isbn, Date releaseDate, boolean isBorrowed, String userWhoBorrowed, Timestamp borrowingDate) {
        this.id = id;
        this.title = title;
        this.authors = authors;
        this.publisher = publisher;
        this.isbn = isbn;
        this.releaseDate = releaseDate;
        this.isBorrowed = isBorrowed;
        this.userWhoBorrowed = userWhoBorrowed;
        this.borrowingDate = borrowingDate;
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

    public List<AuthorSharepointDto> getAuthors() {
        return authors;
    }

    public void setAuthors(List<AuthorSharepointDto> authors) {
        this.authors = authors;
    }

    public PublisherSharepointDto getPublisher() {
        return publisher;
    }

    public void setPublisher(PublisherSharepointDto publisher) {
        this.publisher = publisher;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public boolean isBorrowed() {
        return isBorrowed;
    }

    public void setBorrowed(boolean borrowed) {
        isBorrowed = borrowed;
    }

    public String getUserWhoBorrowed() {
        return userWhoBorrowed;
    }

    public void setUserWhoBorrowed(String userWhoBorrowed) {
        this.userWhoBorrowed = userWhoBorrowed;
    }

    public Timestamp getBorrowingDate() {
        return borrowingDate;
    }

    public void setBorrowingDate(Timestamp borrowingDate) {
        this.borrowingDate = borrowingDate;
    }
}
