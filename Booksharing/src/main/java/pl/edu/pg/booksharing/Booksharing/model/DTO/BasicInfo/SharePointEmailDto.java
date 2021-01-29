package pl.edu.pg.booksharing.Booksharing.model.DTO.BasicInfo;

public class SharePointEmailDto {

    private UserEmailDto user;

    public SharePointEmailDto(UserEmailDto user) {
        this.user = user;
    }

    public SharePointEmailDto() {
    }




    public UserEmailDto getUser() {
        return user;
    }

    public void setUser(UserEmailDto user) {
        this.user = user;
    }
}
