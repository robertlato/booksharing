package pl.edu.pg.booksharing.Booksharing.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pl.edu.pg.booksharing.Booksharing.component.AuthenticationFacadeImpl;
import pl.edu.pg.booksharing.Booksharing.model.Address;
import pl.edu.pg.booksharing.Booksharing.model.DTO.SharepointBooks.SharepointInfoDto;
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
public class SharePointServiceImpl implements SharePointService {

    private SharePointRepository sharePointRepository;
    private ModelMapper modelMapper;
    private AuthenticationFacadeImpl authenticationFacade;
    private UserRepository userRepository;
    private AddressRepository addressRepository;
    private AddressService addressService;


    @Autowired
    public SharePointServiceImpl(SharePointRepository sharePointRepository, ModelMapper modelMapper, AuthenticationFacadeImpl authenticationFacade, UserRepository userRepository, AddressRepository addressRepository, AddressService addressService) {
        this.sharePointRepository = sharePointRepository;
        this.modelMapper = modelMapper;
        this.authenticationFacade = authenticationFacade;
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
        this.addressService = addressService;
    }





    @Override
    public List<SharePoint> findAll() {
        return sharePointRepository.findAll();
    }

    @Override
    public void save(SharePoint sharePoint) {
        sharePointRepository.save(sharePoint);
    }

    @Override
    public SharePoint findById(long id) {
        return sharePointRepository.findById(id);
    }

    @Override
    public SharePoint findByEmail(String email) {
        User user = userRepository.findByEmail(email);

        return sharePointRepository.findById((long) user.getSharePoints().get(0).getId());
    }

    @Override
    public void update(String email, Address address) {
        Authentication authentication = authenticationFacade.getAuthentication();
        String userEmail = authentication.getName();
        if (userEmail.equals(email)) {
            User user = userRepository.findByEmail(authentication.getName());

            Address userSharePointAddress = user.getSharePoints().get(0).getAddress();

            if (!address.getCountry().equals("")) {
                userSharePointAddress.setCountry(address.getCountry());
            }

            if (!address.getCity().equals("")) {
                userSharePointAddress.setCity(address.getCity());
            }

            if (!address.getPostalCode().equals("")) {
                userSharePointAddress.setPostalCode(address.getPostalCode());
            }

            if (!address.getStreet().equals("")) {
                userSharePointAddress.setStreet(address.getStreet());
            }

            if (!address.getHouseNumber().equals("")) {
                userSharePointAddress.setHouseNumber(address.getHouseNumber());
            }
//
//            if (address.getLat() != 0) {
//                userSharePointAddress.setLat(address.getLat());
//            }
//
//            if (address.getLon() != 0) {
//                userSharePointAddress.setLon(address.getLon());
//            }

            if (address.getLat() != 0 && address.getLon() != 0) {
                userSharePointAddress.setLat(address.getLat());
                userSharePointAddress.setLon(address.getLon());
            }

            addressRepository.save(userSharePointAddress);
        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        throw new ResponseStatusException(HttpStatus.NO_CONTENT);
    }

    @Override
    public SharepointInfoDto convertToDto(SharePoint sharePoint) {
        SharepointInfoDto sharepointInfoDto = modelMapper.map(sharePoint, SharepointInfoDto.class);

        return sharepointInfoDto;
    }

    @Override
    public List<SharePoint> findAllByAddressList(String city) {
        List<SharePoint> sharePointsByAddress = new ArrayList<>();
        List<Address> addresses = addressService.findByCity(city);
        for (Address address:
                addresses) {
            if (address.getSharePoint() != null) {
                sharePointsByAddress.add(address.getSharePoint());
            }
        }
        return sharePointsByAddress;
    }
}
