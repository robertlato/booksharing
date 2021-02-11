package pl.edu.pg.booksharing.Booksharing.service;

import pl.edu.pg.booksharing.Booksharing.model.Address;
import pl.edu.pg.booksharing.Booksharing.model.DTO.SharepointBooks.SharepointInfoDto;
import pl.edu.pg.booksharing.Booksharing.model.SharePoint;

import java.util.List;

public interface SharePointService {

    List<SharePoint> findAll();

    void save(SharePoint sharePoint);

    SharePoint findById(long id);

    void update(String email, Address address);

    SharepointInfoDto convertToDto(SharePoint sharePoint);

    SharePoint findByEmail(String email);

    List<SharePoint> findAllByAddressList(String city);
}
