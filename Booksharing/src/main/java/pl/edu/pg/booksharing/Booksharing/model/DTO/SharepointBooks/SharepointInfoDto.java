package pl.edu.pg.booksharing.Booksharing.model.DTO.SharepointBooks;

import pl.edu.pg.booksharing.Booksharing.model.User;

import java.util.ArrayList;
import java.util.List;

public class SharepointInfoDto {

    //private UserSharepointDto user;

    private List<BookSharepointDto> books = new ArrayList<>();

    public SharepointInfoDto( List<BookSharepointDto> books) {
        //this.user = user;
        this.books = books;
    }

    public SharepointInfoDto() {
    }

/*    public UserSharepointDto getUser() {
        return user;
    }

    public void setUser(UserSharepointDto user) {
        this.user = user;
    }*/

    public List<BookSharepointDto> getBooks() {
        return books;
    }

    public void setBooks(List<BookSharepointDto> books) {
        this.books = books;
    }
}
