package pl.edu.pg.booksharing.Booksharing.model.DTO.PublishersList;

import pl.edu.pg.booksharing.Booksharing.model.DTO.AuthorsList.PublisherInfoForAuthorsListDto;
import pl.edu.pg.booksharing.Booksharing.model.Genre;

public class BooksForPublisherListDto {

    private Long id;

    private String title;

    private String isbn;

    private  AuthorsForPublisherListDto authorsForPublisherListDto;

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

    public AuthorsForPublisherListDto getAuthorsForPublisherListDto() {
        return authorsForPublisherListDto;
    }

    public void setAuthorsForPublisherListDto(AuthorsForPublisherListDto authorsForPublisherListDto) {
        this.authorsForPublisherListDto = authorsForPublisherListDto;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }
}
