package pl.edu.pg.booksharing.Booksharing.model.DTO.SharepointBooks;

import pl.edu.pg.booksharing.Booksharing.model.SharePoint;

import java.util.List;

public class UserSharepointDto {

   // private String email;

    private SharepointInfoDto sharePoint;

 /*   public UserSharepointDto(String email) {
        this.email = email;
    }*/

    public UserSharepointDto() {
    }

    public UserSharepointDto( SharepointInfoDto sharePoint) {
       // this.email = email;
        this.sharePoint =  sharePoint;
    }

    /*public String getEmail() {
        return email;
    }*/

/*
    public void setEmail(String email) {
        this.email = email;
    }
*/



    public SharepointInfoDto getSharePoint() {
        return sharePoint;
    }

    public void setSharePoints(SharepointInfoDto sharePoint) {
        this.sharePoint = sharePoint;
    }
}
