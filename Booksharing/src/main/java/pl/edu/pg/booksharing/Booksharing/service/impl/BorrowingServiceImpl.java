package pl.edu.pg.booksharing.Booksharing.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import pl.edu.pg.booksharing.Booksharing.component.AuthenticationFacadeImpl;
import pl.edu.pg.booksharing.Booksharing.exception.BorrowedAlreadyException;
import pl.edu.pg.booksharing.Booksharing.exception.ResourceNotFoundException;
import pl.edu.pg.booksharing.Booksharing.model.*;
import pl.edu.pg.booksharing.Booksharing.model.DTO.BorrowingInfo.BorrowingListInfoDto;
import pl.edu.pg.booksharing.Booksharing.model.DTO.BorrowingBook.BorrowingDto;
import pl.edu.pg.booksharing.Booksharing.model.DTO.BorrowingBook.BorrowingReturnDto;
import pl.edu.pg.booksharing.Booksharing.repository.BookRepository;
import pl.edu.pg.booksharing.Booksharing.repository.BorrowingRepository;
import pl.edu.pg.booksharing.Booksharing.repository.SharePointRepository;
import pl.edu.pg.booksharing.Booksharing.repository.UserRepository;
import pl.edu.pg.booksharing.Booksharing.service.BookService;
import pl.edu.pg.booksharing.Booksharing.service.BorrowingService;
import pl.edu.pg.booksharing.Booksharing.service.SharePointService;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class BorrowingServiceImpl implements BorrowingService {

    private BorrowingRepository borrowingRepository;
    private BookRepository bookRepository;
    private BookService bookService;
    private UserRepository userRepository;
    private SharePointRepository sharePointRepository;
    private SharePointService sharePointService;
    private AuthenticationFacadeImpl authenticationFacade;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public BorrowingServiceImpl(BorrowingRepository borrowingRepository, BookRepository bookRepository,
                                BookService bookService, UserRepository userRepository,
                                SharePointService sharePointService, AuthenticationFacadeImpl authenticationFacade) {
        this.borrowingRepository = borrowingRepository;
        this.bookRepository = bookRepository;
        this.bookService = bookService;
        this.userRepository = userRepository;
        this.sharePointService = sharePointService;
        this.authenticationFacade = authenticationFacade;
    }

    @Override
    public void borrow(Borrowing borrowing) throws ResourceNotFoundException, BorrowedAlreadyException {
        Book book = bookService.findById(borrowing.getBook().getId());
        if(book.isBorrowed() == true) {
            throw new BorrowedAlreadyException("This book is already borrowed");
        } else {
            book.setBorrowed(true);
            borrowingRepository.save(borrowing);
        }
    }

    @Override
    public Borrowing convertToEntity(BorrowingDto borrowingDto) throws ResourceNotFoundException {
        Authentication authentication = authenticationFacade.getAuthentication();
        User user = userRepository.findByEmail(authentication.getName());
        Book book = bookService.findById(borrowingDto.getBook().getId());
        SharePoint sharePoint = sharePointService.findById(borrowingDto.getSharePoint().getId());

        Borrowing borrowing = new Borrowing(
                borrowingDto.getCheckOutDate(),
                borrowingDto.getCheckInDate(),
                book,
                sharePoint,
                user
        );

        return borrowing;
    }

    @Override
    public Borrowing findById(long id) {
        Borrowing borrowing = borrowingRepository.findById(id);
        return borrowing;
    }

    @Override
    public Borrowing convertToEntityReturn(BorrowingReturnDto borrowingReturnDto) throws ResourceNotFoundException {
        long borrowingId = borrowingReturnDto.getId();
        Borrowing borrowing = borrowingRepository.findById(borrowingId);


        return borrowing;
    }

    @Override
    public void returnBook(Borrowing borrowing) throws ResourceNotFoundException {
        Book book = bookService.findById(borrowing.getBook().getId());
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        borrowing.setCheckInDate(timestamp);
        book.setBorrowed(false);

    }

    @Override
    public List<BorrowingListInfoDto> findAllBorrowings() {
        Authentication authentication = authenticationFacade.getAuthentication();
        User user = userRepository.findByEmail(authentication.getName());
        List<Borrowing> borrowings = user.getBorrowings();

        return borrowings.stream().map(borrowing -> modelMapper.map(borrowing, BorrowingListInfoDto.class)).collect(Collectors.toList());
    }
}
