package pl.edu.pg.booksharing.Booksharing.service;

import pl.edu.pg.booksharing.Booksharing.model.Address;
import pl.edu.pg.booksharing.Booksharing.model.Author;
import pl.edu.pg.booksharing.Booksharing.model.DTO.UsersAccountSettings.AddressSettingsDto;

import java.util.List;

public interface AddressService {

    void save(Address address);

    AddressSettingsDto getAddressForSettings();

    List<Address> findByCity(String city);
}
