package pl.edu.pg.booksharing.Booksharing.model.DTO.BasicInfo;

public class AuthorInfoForBookDto {

    private String firstName;


    private String lastName;



    public AuthorInfoForBookDto(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public AuthorInfoForBookDto() {
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
