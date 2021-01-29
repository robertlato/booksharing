package pl.edu.pg.booksharing.Booksharing.model.DTO.SearchBook;

import pl.edu.pg.booksharing.Booksharing.model.Address;
import pl.edu.pg.booksharing.Booksharing.model.Book;

import java.util.ArrayList;
import java.util.List;

public class SharePointSearchDto {

    private AddressSearchDto address;


    public SharePointSearchDto(AddressSearchDto address)
    {
        this.address = address;
    }

    public SharePointSearchDto() {
    }

    public AddressSearchDto getAddress() {
        return address;
    }

    public void setAddress(AddressSearchDto address) {
        this.address = address;
    }
}
