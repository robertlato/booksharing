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
import pl.edu.pg.booksharing.Booksharing.service.SharePointService;

import java.util.List;

@Service
public class SharePointServiceImpl implements SharePointService {

    private SharePointRepository sharePointRepository;
    private ModelMapper modelMapper;
    private AuthenticationFacadeImpl authenticationFacade;
    private UserRepository userRepository;
    private AddressRepository addressRepository;


    @Autowired
    public SharePointServiceImpl(SharePointRepository sharePointRepository, ModelMapper modelMapper, AuthenticationFacadeImpl authenticationFacade, UserRepository userRepository, AddressRepository addressRepository) {
        this.sharePointRepository = sharePointRepository;
        this.modelMapper = modelMapper;
        this.authenticationFacade = authenticationFacade;
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
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
    public void update(String email, Address address) {
        Authentication authentication = authenticationFacade.getAuthentication();
        String userEmail = authentication.getName();
        if (userEmail.equals(email)) {
            User user = userRepository.findByEmail(authentication.getName());

            Address userSharePointAddress = user.getSharePoints().get(0).getAddress();

            if (address.getCountry() != null) {
                userSharePointAddress.setCountry(address.getCountry());
            }

            if (address.getCity() != null) {
                userSharePointAddress.setCity(address.getCity());
            }

            if (address.getPostalCode() != null) {
                userSharePointAddress.setPostalCode(address.getPostalCode());
            }

            if (address.getStreet() != null) {
                userSharePointAddress.setStreet(address.getStreet());
            }

            if (address.getHouseNumber() != null) {
                userSharePointAddress.setHouseNumber(address.getHouseNumber());
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
}
