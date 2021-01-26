package pl.edu.pg.booksharing.Booksharing.model.DTO.BasicInfo;

import pl.edu.pg.booksharing.Booksharing.model.Genre;


import java.util.ArrayList;
import java.util.List;

public class BookBasicInfoDto {
    private Long id;

    private String title;

    private String isbn;

    private List<AuthorInfoForBookDto> authors = new ArrayList<>();

    private PublisherInfoForBookDto publisherInfoForBookDto;

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

    public List<AuthorInfoForBookDto> getAuthors() {
        return authors;
    }

    public void setAuthors(List<AuthorInfoForBookDto> authors) {
        this.authors = authors;
    }

    public PublisherInfoForBookDto getPublisher() {
        return publisherInfoForBookDto;
    }

    public void setPublisher(PublisherInfoForBookDto publisherInfoForBookDto) {
        this.publisherInfoForBookDto = publisherInfoForBookDto;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }


}
