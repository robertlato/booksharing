package pl.edu.pg.booksharing.Booksharing.model.DTO.AuthorsList;

import java.util.ArrayList;
import java.util.List;

public class AuthorInfoDto {
    private Long id;

    private String firstName;

    private String lastName;

    private List<BooksForAuthorsListDto> books = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<BooksForAuthorsListDto> getBooks() {
        return books;
    }

    public void setBooks(List<BooksForAuthorsListDto> books) {
        this.books = books;
    }
}
