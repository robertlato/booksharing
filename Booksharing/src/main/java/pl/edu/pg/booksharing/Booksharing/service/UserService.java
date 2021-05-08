package pl.edu.pg.booksharing.Booksharing.service;


import pl.edu.pg.booksharing.Booksharing.exception.EmailAlreadyTakenException;
import pl.edu.pg.booksharing.Booksharing.exception.ResourceNotFoundException;
import pl.edu.pg.booksharing.Booksharing.model.DTO.SharepointBooks.UserSharepointDto;
import pl.edu.pg.booksharing.Booksharing.model.DTO.UsersAccountSettings.UserSettingsDto;
import pl.edu.pg.booksharing.Booksharing.model.User;

public interface UserService {

    void save(User user) throws EmailAlreadyTakenException;

    User findByEmail(String email) throws ResourceNotFoundException;

    User findById(long id) throws ResourceNotFoundException;

    UserSettingsDto getUserForSettings();

    UserSharepointDto convertToDto(User user);

    void update(String ownerEmail, User user);


    void updatePassword(String password);

    void delete();

}

