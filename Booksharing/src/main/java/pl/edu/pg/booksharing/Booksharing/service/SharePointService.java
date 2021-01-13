package pl.edu.pg.booksharing.Booksharing.service;

import pl.edu.pg.booksharing.Booksharing.model.SharePoint;

import java.util.List;

public interface SharePointService {

    List<SharePoint> findAll();

    void save(SharePoint sharePoint);

    SharePoint findById(long id);

    void update(SharePoint sharePoint);
}
