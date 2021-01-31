package pl.edu.pg.booksharing.Booksharing.model.DTO.SearchBook;


public class SharePointSearchDto {

    private Long id;

    private AddressSearchDto address;


    public SharePointSearchDto() {
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
