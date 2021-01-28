package pl.edu.pg.booksharing.Booksharing.model.DTO.BasicInfo;

import pl.edu.pg.booksharing.Booksharing.model.Genre;


import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class BookBasicInfoDto {
//    private Long id;

    private String title;

    private String isbn;

    private List<AuthorInfoForBookDto> authors = new ArrayList<>();

    private PublisherInfoForBookDto publisher;

    private Genre genre;

    private String sharePointOwnerEmail;

    private java.sql.Date releaseDate;


    public BookBasicInfoDto() {
    }

    public BookBasicInfoDto(String title, String isbn, List<AuthorInfoForBookDto> authors,
                            PublisherInfoForBookDto publisher, Genre genre, String sharePointOwnerEmail, Date releaseDate) {
        this.title = title;
        this.isbn = isbn;
        this.authors = authors;
        this.publisher = publisher;
        this.genre = genre;
        this.sharePointOwnerEmail = sharePointOwnerEmail;
        this.releaseDate = releaseDate;
    }


    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public PublisherInfoForBookDto getPublisher() {
        return publisher;
    }

    public void setPublisher(PublisherInfoForBookDto publisher) {
        this.publisher = publisher;
    }

    public String getSharePointOwnerEmail() {
        return sharePointOwnerEmail;
    }

    public void setSharePointOwnerEmail(String sharePointOwnerEmail) {
        this.sharePointOwnerEmail = sharePointOwnerEmail;
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

    public List<AuthorInfoForBookDto> getAuthors() {
        return authors;
    }

    public void setAuthors(List<AuthorInfoForBookDto> authors) {
        this.authors = authors;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }


}
