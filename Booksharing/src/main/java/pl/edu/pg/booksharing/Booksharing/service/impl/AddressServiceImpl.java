package pl.edu.pg.booksharing.Booksharing.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pg.booksharing.Booksharing.model.Address;
import pl.edu.pg.booksharing.Booksharing.model.Author;
import pl.edu.pg.booksharing.Booksharing.repository.AddressRepository;
import pl.edu.pg.booksharing.Booksharing.service.AddressService;

import java.util.ArrayList;
import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    private AddressRepository addressRepository;

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public void save(Address address) {
        addressRepository.save(address);
    }

    @Override
    public List<Address> findByCity(String city) {
        List<Address> allAddresses = addressRepository.findAll();
        List<Address> addressesCity = new ArrayList<>();
        for (Address address:
             allAddresses) {
            if (address.getCity().equalsIgnoreCase(city)) {
                addressesCity.add(address);
            }
        }
        return addressesCity;
    }
}
