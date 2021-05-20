package pl.edu.pg.booksharing.Booksharing.model.DTO.Password;

public class UserPasswordDto {

    private String password;

    public UserPasswordDto(String password) {
        this.password = password;
    }

    public UserPasswordDto() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
