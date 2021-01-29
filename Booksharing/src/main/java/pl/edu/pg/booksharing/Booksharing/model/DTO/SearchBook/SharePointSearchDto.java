package pl.edu.pg.booksharing.Booksharing.model.DTO.SearchBook;


public class SharePointSearchDto {

    private AddressSearchDto address;


    public SharePointSearchDto() {
    }

    public AddressSearchDto getAddress() {
        return address;
    }

    public void setAddress(AddressSearchDto address) {
        this.address = address;
    }
}
