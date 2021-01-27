package pl.edu.pg.booksharing.Booksharing.model.DTO.PublishersList;

import java.util.ArrayList;
import java.util.List;

public class PublisherInfoDto {

    private Long id;

    private String name;

    private List<BooksForPublisherListDto> booksForPublisherListDto = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<BooksForPublisherListDto> getBooks() {
        return booksForPublisherListDto;
    }

    public void setBooks(List<BooksForPublisherListDto> booksForPublisherListDto) {
        this.booksForPublisherListDto = booksForPublisherListDto;
    }
}
