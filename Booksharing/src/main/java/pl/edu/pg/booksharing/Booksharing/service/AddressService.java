package pl.edu.pg.booksharing.Booksharing.service;

import pl.edu.pg.booksharing.Booksharing.model.Address;
import pl.edu.pg.booksharing.Booksharing.model.Author;

import java.util.List;

public interface AddressService {

    void save(Address address);

    List<Address> findByCity(String city);
}
