package pl.edu.pg.booksharing.Booksharing.model.DTO.BasicInfo;

public class UserEmailDto {
    private String email;


    public UserEmailDto(String email) {
        this.email = email;
    }

    public UserEmailDto() {
    }




    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
