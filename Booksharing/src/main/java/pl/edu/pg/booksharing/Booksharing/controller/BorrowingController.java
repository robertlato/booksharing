package pl.edu.pg.booksharing.Booksharing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.edu.pg.booksharing.Booksharing.exception.BorrowedAlreadyException;
import pl.edu.pg.booksharing.Booksharing.exception.ResourceNotFoundException;
import pl.edu.pg.booksharing.Booksharing.model.Borrowing;
import pl.edu.pg.booksharing.Booksharing.model.DTO.BorrowingInfo.BorrowingListInfoDto;
import pl.edu.pg.booksharing.Booksharing.model.DTO.BorrowingBook.BorrowingDto;
import pl.edu.pg.booksharing.Booksharing.model.DTO.BorrowingBook.BorrowingReturnDto;
import pl.edu.pg.booksharing.Booksharing.service.BorrowingService;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins={ "http://localhost:8889", "http://localhost:3000" }, maxAge = 3600, allowedHeaders = "*")
@RestController
public class BorrowingController {

    private BorrowingService borrowingService;

    @Autowired
    public BorrowingController(BorrowingService borrowingService) {
        this.borrowingService = borrowingService;
    }

    @PostMapping(path = "/api/borrowing")
    public void addBorrowing(@Valid @RequestBody BorrowingDto borrowingDto) throws ResourceNotFoundException, BorrowedAlreadyException {
        Borrowing borrowing = borrowingService.convertToEntity(borrowingDto);
        borrowingService.borrow(borrowing);
    }

    @PostMapping(path = "/api/borrowing/return")
    public void returnBookBorrowing(@Valid @RequestBody BorrowingReturnDto borrowingReturnDto) throws ResourceNotFoundException {
        Borrowing borrowing = borrowingService.convertToEntityReturn(borrowingReturnDto);
        borrowingService.returnBook(borrowing);
    }

    @GetMapping(path = "/api/user/borrowings")
    public List<BorrowingListInfoDto> getAllBorrowingsForUser() {
        List<BorrowingListInfoDto> borrowings = borrowingService.findAllBorrowings();

        return borrowings;
    }
}
