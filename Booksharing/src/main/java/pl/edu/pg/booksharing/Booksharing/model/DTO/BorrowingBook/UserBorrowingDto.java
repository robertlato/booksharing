package pl.edu.pg.booksharing.Booksharing.model.DTO.BorrowingBook;

public class UserBorrowingDto {

    private String email;


    public UserBorrowingDto() {
    }

    public UserBorrowingDto(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
