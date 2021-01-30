package pl.edu.pg.booksharing.Booksharing.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pl.edu.pg.booksharing.Booksharing.component.AuthenticationFacadeImpl;
import pl.edu.pg.booksharing.Booksharing.exception.EmailAlreadyTakenException;
import pl.edu.pg.booksharing.Booksharing.exception.ResourceNotFoundException;
import pl.edu.pg.booksharing.Booksharing.model.Address;
import pl.edu.pg.booksharing.Booksharing.model.DTO.SharepointBooks.UserSharepointDto;
import pl.edu.pg.booksharing.Booksharing.model.SharePoint;
import pl.edu.pg.booksharing.Booksharing.model.User;
import pl.edu.pg.booksharing.Booksharing.repository.UserRepository;
import pl.edu.pg.booksharing.Booksharing.service.SharePointService;
import pl.edu.pg.booksharing.Booksharing.service.UserService;


@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private SharePointService sharePointService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private AuthenticationFacadeImpl authenticationFacade;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, SharePointService sharePointService,
                           BCryptPasswordEncoder bCryptPasswordEncoder, AuthenticationFacadeImpl authenticationFacade) {
        this.userRepository = userRepository;
        this.sharePointService = sharePointService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.authenticationFacade = authenticationFacade;
    }

    // when registering a new user create also
    // sharepoint and address and link
    // it with user
    @Override
    public void save(User user) throws EmailAlreadyTakenException {
        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new EmailAlreadyTakenException("Email: " + user.getEmail() + " is already taken");
        } else {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

            Address address = new Address();
            SharePoint sharePoint = new SharePoint(user, address);
            address.setSharePoint(sharePoint);

            user.addSharePoint(sharePoint);

            userRepository.save(user);
            sharePointService.save(sharePoint);
        }
    }

    @Override
    public User findByEmail(String email) throws ResourceNotFoundException {
        if (userRepository.findByEmail(email) == null) {
            throw new ResourceNotFoundException("User with " + email + " email is not found");
        } else {
            return userRepository.findByEmail(email);
        }
    }

    @Override
    public User findById(long id) throws ResourceNotFoundException {
        if (userRepository.findById(id) == null) {
            throw new ResourceNotFoundException("User with id: " + id + "  is not found");
        } else {
            return userRepository.findById(id);
        }
    }

    @Override
    public UserSharepointDto convertToDto(User user) {
        UserSharepointDto userSharepointDto = modelMapper.map(user, UserSharepointDto.class);

        return userSharepointDto;
    }

    @Override
    public void update(String ownerEmail, User user) {
        Authentication authentication = authenticationFacade.getAuthentication();
        String userEmail = authentication.getName();
        if (userEmail.equals(ownerEmail)) {
            User existingUser = userRepository.findByEmail(authentication.getName());

            if (user.getFirstName() != null) {
                existingUser.setFirstName(user.getFirstName());
            }

            if (user.getLastName() != null) {
                existingUser.setLastName(user.getLastName());
            }

            if (user.getPhoneNumber() != null) {
                existingUser.setPhoneNumber(user.getPhoneNumber());
            }

            if (user.getEmail() != null) {
                existingUser.setEmail(user.getEmail());
            }

            if (user.getPassword() != null) {
                existingUser.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            }

            userRepository.save(existingUser);

        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        throw new ResponseStatusException(HttpStatus.NO_CONTENT);
    }
}

