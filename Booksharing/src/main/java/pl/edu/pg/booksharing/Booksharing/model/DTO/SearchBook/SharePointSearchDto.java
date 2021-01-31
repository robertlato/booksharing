package pl.edu.pg.booksharing.Booksharing.model.DTO.SearchBook;


import pl.edu.pg.booksharing.Booksharing.model.DTO.BasicInfo.UserEmailDto;

public class SharePointSearchDto {

    private Long id;

    private AddressSearchDto address;

    private UserEmailDto user;



    public SharePointSearchDto() {
    }


    public UserEmailDto getUser() {
        return user;
    }

    public void setUser(UserEmailDto user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AddressSearchDto getAddress() {
        return address;
    }

    public void setAddress(AddressSearchDto address) {
        this.address = address;
    }
}
