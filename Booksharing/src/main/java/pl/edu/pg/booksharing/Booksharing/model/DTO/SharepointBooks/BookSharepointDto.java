package pl.edu.pg.booksharing.Booksharing.model.DTO.SharepointBooks;

import pl.edu.pg.booksharing.Booksharing.model.Publisher;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class BookSharepointDto {

    private String title;

    private List<AuthorSharepointDto> authors = new ArrayList<>();

    private PublisherSharepointDto publisher;

    private String isbn;

    private java.sql.Date releaseDate;

    private boolean isBorrowed;

    public BookSharepointDto() {
    }

    public BookSharepointDto(String title, List<AuthorSharepointDto> authors, PublisherSharepointDto publisher,
                             String isbn, Date releaseDate, boolean isBorrowed) {
        this.title = title;
        this.authors = authors;
        this.publisher = publisher;
        this.isbn = isbn;
        this.releaseDate = releaseDate;
        this.isBorrowed = isBorrowed;
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
}
