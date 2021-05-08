package pl.edu.pg.booksharing.Booksharing.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import pl.edu.pg.booksharing.Booksharing.component.AuthenticationFacadeImpl;
import pl.edu.pg.booksharing.Booksharing.component.IAuthenticationFacade;
import pl.edu.pg.booksharing.Booksharing.model.Address;
import pl.edu.pg.booksharing.Booksharing.model.Author;
import pl.edu.pg.booksharing.Booksharing.model.DTO.UsersAccountSettings.AddressSettingsDto;
import pl.edu.pg.booksharing.Booksharing.model.SharePoint;
import pl.edu.pg.booksharing.Booksharing.model.User;
import pl.edu.pg.booksharing.Booksharing.repository.AddressRepository;
import pl.edu.pg.booksharing.Booksharing.repository.SharePointRepository;
import pl.edu.pg.booksharing.Booksharing.repository.UserRepository;
import pl.edu.pg.booksharing.Booksharing.service.AddressService;
import pl.edu.pg.booksharing.Booksharing.service.SharePointService;

import java.util.ArrayList;
import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    private AddressRepository addressRepository;
    private AuthenticationFacadeImpl authenticationFacade;
    private UserRepository userRepository;
    private SharePointService sharePointService;
    private ModelMapper modelMapper;

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository,ModelMapper modelMapper,
                              AuthenticationFacadeImpl authenticationFacade, UserRepository userRepository,
                              @Lazy SharePointService sharePointService) {
        this.addressRepository = addressRepository;
        this.modelMapper = modelMapper;
        this.authenticationFacade = authenticationFacade;
        this.userRepository = userRepository;
        this.sharePointService = sharePointService;
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
            if (address.getCity() != null) {
                if (address.getCity().equalsIgnoreCase(city)) {
                    addressesCity.add(address);
                }
            }
        }
        return addressesCity;
    }

    @Override
    public AddressSettingsDto getAddressForSettings() {
        Authentication authentication = authenticationFacade.getAuthentication();
        User user = userRepository.findByEmail(authentication.getName());
        SharePoint sharePoint = sharePointService.findByEmail(user.getEmail());
        Address address = sharePoint.getAddress();
        AddressSettingsDto addressSettingsDto = modelMapper.map(address, AddressSettingsDto.class);

        return addressSettingsDto;
    }
}
