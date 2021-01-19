package pl.edu.pg.booksharing.Booksharing.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pg.booksharing.Booksharing.model.SharePoint;
import pl.edu.pg.booksharing.Booksharing.repository.SharePointRepository;
import pl.edu.pg.booksharing.Booksharing.service.SharePointService;

import java.util.List;

@Service
public class SharePointServiceImpl implements SharePointService {

    private SharePointRepository sharePointRepository;

    @Autowired
    public SharePointServiceImpl(SharePointRepository sharePointRepository) {
        this.sharePointRepository = sharePointRepository;
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
    public void update(SharePoint sharePoint) {
        sharePointRepository.save(sharePoint);
    }
}