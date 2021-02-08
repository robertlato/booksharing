package pl.edu.pg.booksharing.Booksharing.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pg.booksharing.Booksharing.model.Address;
import pl.edu.pg.booksharing.Booksharing.model.Borrowing;
import pl.edu.pg.booksharing.Booksharing.model.SharePoint;
import pl.edu.pg.booksharing.Booksharing.repository.BorrowingRepository;
import pl.edu.pg.booksharing.Booksharing.repository.SharePointRepository;
import pl.edu.pg.booksharing.Booksharing.service.AddressService;
import pl.edu.pg.booksharing.Booksharing.service.BookService;
import pl.edu.pg.booksharing.Booksharing.service.SharePointService;
import pl.edu.pg.booksharing.Booksharing.service.StatsService;

import java.util.*;

@Service
public class StatsServiceImpl implements StatsService {

    private SharePointRepository sharePointRepository;
    private AddressService addressService;
    private BorrowingRepository borrowingRepository;
    private BookService bookService;
    private SharePointService sharePointService;

    @Autowired
    public StatsServiceImpl(SharePointRepository sharePointRepository, AddressService addressService,
                            BorrowingRepository borrowingRepository, BookService bookService, SharePointService sharePointService) {
        this.sharePointRepository = sharePointRepository;
        this.addressService = addressService;
        this.borrowingRepository = borrowingRepository;
        this.bookService = bookService;
        this.sharePointService = sharePointService;
    }

    @Override
    public int getNumberOfBorrowingsPerCity(String city) {
        List<Borrowing> borrowings = new ArrayList<>();
        List<SharePoint> sharePoints = sharePointService.findAllByAddressList(city);
        for (SharePoint sharePoint:
                sharePoints) {
            List<Borrowing> borrowingsFromSharePoint = sharePoint.getBorrowings();
            for (Borrowing borrowing:
                    borrowingsFromSharePoint) {
                borrowings.add(borrowing);
            }
        }

        int numberOfBorrowings = borrowings.size();

        return numberOfBorrowings;
    }

    @Override
    public String getMostPopularSharePoint() {
        List<SharePoint> sharePoints = sharePointRepository.findAll();
        List<Borrowing> borrowings = new ArrayList<>();
        Map<Long, Integer> sharePointBorrowingNumberMap = new HashMap<Long, Integer>();
        for (SharePoint sharePoint:
             sharePoints) {
            borrowings = sharePoint.getBorrowings();
            sharePointBorrowingNumberMap.put(sharePoint.getId(), borrowings.size());
        }
        Long idOfMostPopularSharePoint = Collections.max(sharePointBorrowingNumberMap.entrySet(), Map.Entry.comparingByValue()).getKey();
        Integer numberOfBorrowings = Collections.max(sharePointBorrowingNumberMap.entrySet(), Map.Entry.comparingByValue()).getValue();

        SharePoint mostPopularSharePoint = sharePointService.findById(idOfMostPopularSharePoint);
        Address addressSharePoint = mostPopularSharePoint.getAddress();
        String city = addressSharePoint.getCity();

        return "{ \"city\": \"" + city + "\", \"number\": \"" + numberOfBorrowings +"\" }";
    }

    @Override
    public List<String> getMostPopularBooks() {
        return null;
    }
}
