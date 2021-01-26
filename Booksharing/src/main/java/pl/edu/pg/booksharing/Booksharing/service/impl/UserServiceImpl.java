package pl.edu.pg.booksharing.Booksharing.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.edu.pg.booksharing.Booksharing.exception.EmailAlreadyTakenException;
import pl.edu.pg.booksharing.Booksharing.exception.ResourceNotFoundException;
import pl.edu.pg.booksharing.Booksharing.model.Address;
import pl.edu.pg.booksharing.Booksharing.model.SharePoint;
import pl.edu.pg.booksharing.Booksharing.model.User;
import pl.edu.pg.booksharing.Booksharing.repository.UserRepository;
import pl.edu.pg.booksharing.Booksharing.service.SharePointService;
import pl.edu.pg.booksharing.Booksharing.service.UserService;

import java.util.ArrayList;


@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private SharePointService sharePointService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, SharePointService sharePointService,
                           BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.sharePointService = sharePointService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
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
}

