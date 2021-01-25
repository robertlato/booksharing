package pl.edu.pg.booksharing.Booksharing.model.DTO;

import java.util.ArrayList;
import java.util.List;

public class AuthorDto {

    private Long id;

    private String firstName;

    private String secondName;

    private String lastName;

    //private List<BookDto> books = new ArrayList<>();

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

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

/*    public List<BookDto> getBooks() {
        return books;
    }

    public void setBooks(List<BookDto> books) {
        this.books = books;
    }*/

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
