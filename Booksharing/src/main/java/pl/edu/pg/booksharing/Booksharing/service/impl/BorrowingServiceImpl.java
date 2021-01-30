package pl.edu.pg.booksharing.Booksharing.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pg.booksharing.Booksharing.exception.BorrowedAlreadyException;
import pl.edu.pg.booksharing.Booksharing.exception.ResourceNotFoundException;
import pl.edu.pg.booksharing.Booksharing.model.Book;
import pl.edu.pg.booksharing.Booksharing.model.Borrowing;
import pl.edu.pg.booksharing.Booksharing.model.DTO.BorrowingBook.BorrowingDto;
import pl.edu.pg.booksharing.Booksharing.model.SharePoint;
import pl.edu.pg.booksharing.Booksharing.model.User;
import pl.edu.pg.booksharing.Booksharing.repository.BookRepository;
import pl.edu.pg.booksharing.Booksharing.repository.BorrowingRepository;
import pl.edu.pg.booksharing.Booksharing.repository.SharePointRepository;
import pl.edu.pg.booksharing.Booksharing.repository.UserRepository;
import pl.edu.pg.booksharing.Booksharing.service.BookService;
import pl.edu.pg.booksharing.Booksharing.service.BorrowingService;
import pl.edu.pg.booksharing.Booksharing.service.SharePointService;


@Service
public class BorrowingServiceImpl implements BorrowingService {

    private BorrowingRepository borrowingRepository;
    private BookRepository bookRepository;
    private BookService bookService;
    private UserRepository userRepository;
    private SharePointRepository sharePointRepository;
    private SharePointService sharePointService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public BorrowingServiceImpl(BorrowingRepository borrowingRepository, BookRepository bookRepository,
                                BookService bookService, UserRepository userRepository,
                                SharePointService sharePointService) {
        this.borrowingRepository = borrowingRepository;
        this.bookRepository = bookRepository;
        this.bookService = bookService;
        this.userRepository = userRepository;
        this.sharePointService = sharePointService;
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
        User user = userRepository.findByEmail(borrowingDto.getUser().getEmail());
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
}
