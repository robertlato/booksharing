package pl.edu.pg.booksharing.Booksharing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.edu.pg.booksharing.Booksharing.model.Address;
import pl.edu.pg.booksharing.Booksharing.model.SharePoint;
import pl.edu.pg.booksharing.Booksharing.service.SharePointService;

import java.util.List;

@CrossOrigin(origins={ "http://localhost:8889", "http://localhost:3000" }, maxAge = 3600, allowedHeaders = "*")
@RestController
public class SharePointController {

    private SharePointService sharePointService;

    @Autowired
    public SharePointController(SharePointService sharePointService) {
        this.sharePointService = sharePointService;
    }

    @GetMapping(path = "/api/sharepoints")
    public List<SharePoint> getAllSharePoints() {
        return sharePointService.findAll();
    }

    @GetMapping(path = "/api/sharepoint/{id}")
    public SharePoint getSharePoint(@PathVariable long id) {
        return sharePointService.findById(id);
    }

    // update address of the sharepoint
    // user remains unchanged
    @PatchMapping(path = "/api/sharepoint/{ownerEmail}")
    public void updateSharepoint(@PathVariable String ownerEmail, @RequestBody Address address) {
        sharePointService.update(ownerEmail, address);
    }

    @GetMapping(path = "/api/sharepoint/email/{ownerEmail}")
    public SharePoint getSharePointByEmail(@PathVariable String ownerEmail) {
        return sharePointService.findByEmail(ownerEmail);
    }
}
