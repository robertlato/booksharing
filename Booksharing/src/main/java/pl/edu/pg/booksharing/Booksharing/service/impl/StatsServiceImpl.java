package pl.edu.pg.booksharing.Booksharing.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pg.booksharing.Booksharing.exception.ResourceNotFoundException;
import pl.edu.pg.booksharing.Booksharing.model.Address;
import pl.edu.pg.booksharing.Booksharing.model.Book;
import pl.edu.pg.booksharing.Booksharing.model.Borrowing;
import pl.edu.pg.booksharing.Booksharing.model.SharePoint;
import pl.edu.pg.booksharing.Booksharing.repository.BookRepository;
import pl.edu.pg.booksharing.Booksharing.repository.BorrowingRepository;
import pl.edu.pg.booksharing.Booksharing.repository.SharePointRepository;
import pl.edu.pg.booksharing.Booksharing.service.AddressService;
import pl.edu.pg.booksharing.Booksharing.service.BookService;
import pl.edu.pg.booksharing.Booksharing.service.SharePointService;
import pl.edu.pg.booksharing.Booksharing.service.StatsService;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class StatsServiceImpl implements StatsService {

    private SharePointRepository sharePointRepository;
    private AddressService addressService;
    private BorrowingRepository borrowingRepository;
    private BookService bookService;
    private SharePointService sharePointService;
    private BookRepository bookRepository;

    @Autowired
    public StatsServiceImpl(SharePointRepository sharePointRepository, AddressService addressService,
                            BorrowingRepository borrowingRepository, BookService bookService, SharePointService sharePointService, BookRepository bookRepository) {
        this.sharePointRepository = sharePointRepository;
        this.addressService = addressService;
        this.borrowingRepository = borrowingRepository;
        this.bookService = bookService;
        this.sharePointService = sharePointService;
        this.bookRepository = bookRepository;
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
    public LinkedHashMap<String, Integer> getMostPopularBooks() throws ResourceNotFoundException {
        List<Book> allBooks = bookRepository.findAll();
        List<Borrowing> borrowings = new ArrayList<>();
        Map<String, Integer> isbnAndBorrowings = new HashMap<String, Integer>();
        for (Book book:
             allBooks) {
            borrowings = book.getBorrowings();
            if (isbnAndBorrowings.containsKey(book.getIsbn())){
                Integer previousValue = isbnAndBorrowings.get(book.getIsbn());
                isbnAndBorrowings.put(book.getIsbn(), previousValue + borrowings.size());
            } else {
                isbnAndBorrowings.put(book.getIsbn(), borrowings.size());
            }
        }

        Map<String, Integer> titleAndBorrowings = new HashMap<String, Integer>();


        for (Map.Entry e:
             isbnAndBorrowings.entrySet()) {
            titleAndBorrowings.put(bookService.findByIsbn((String)e.getKey()).get(0).getTitle(), (Integer) e.getValue());
        }

        LinkedHashMap<String, Integer> sortedMap = titleAndBorrowings.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(e -> e.getKey(),
                        e-> e.getValue(),
                        (e1, e2) -> null,
                        () -> new LinkedHashMap<String, Integer>()));

        LinkedHashMap<String, Integer> sortedTopTen = sortedMap.entrySet().stream()
                .limit(10)
                .collect(Collectors.toMap(e -> e.getKey(),
                        e-> e.getValue(),
                        (e1, e2) -> null,
                        () -> new LinkedHashMap<String, Integer>()));

        return sortedTopTen;
    }
}
