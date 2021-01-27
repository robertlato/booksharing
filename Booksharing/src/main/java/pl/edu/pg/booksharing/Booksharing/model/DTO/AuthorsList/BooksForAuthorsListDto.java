package pl.edu.pg.booksharing.Booksharing.model.DTO.AuthorsList;

import pl.edu.pg.booksharing.Booksharing.model.DTO.BasicInfo.PublisherInfoForBookDto;
import pl.edu.pg.booksharing.Booksharing.model.Genre;

public class BooksForAuthorsListDto {

    private Long id;

    private String title;

    private String isbn;

    private PublisherInfoForAuthorsListDto publisherInfoForAuthorsListDto;

    private Genre genre;

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

    public PublisherInfoForAuthorsListDto getPublisher() {
        return publisherInfoForAuthorsListDto;
    }

    public void setPublisher(PublisherInfoForAuthorsListDto publisherInfoForAuthorsListDto) {
        this.publisherInfoForAuthorsListDto = publisherInfoForAuthorsListDto;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }
}
