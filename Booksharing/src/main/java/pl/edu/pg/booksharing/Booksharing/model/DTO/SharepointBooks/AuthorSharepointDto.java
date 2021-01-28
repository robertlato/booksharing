package pl.edu.pg.booksharing.Booksharing.model.DTO.SharepointBooks;

public class AuthorSharepointDto {

    private String firstName;

    private String lastName;

    public AuthorSharepointDto() {
    }

    public AuthorSharepointDto(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
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
