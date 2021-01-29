package pl.edu.pg.booksharing.Booksharing.model.DTO.SearchBook;

public class AuthorSearchDto {

    private String firstName;


    private String lastName;



    public AuthorSearchDto(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public AuthorSearchDto() {
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
}
