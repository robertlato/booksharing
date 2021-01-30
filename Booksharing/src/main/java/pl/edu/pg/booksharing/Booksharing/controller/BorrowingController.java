package pl.edu.pg.booksharing.Booksharing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pg.booksharing.Booksharing.exception.BorrowedAlreadyException;
import pl.edu.pg.booksharing.Booksharing.exception.ResourceNotFoundException;
import pl.edu.pg.booksharing.Booksharing.model.Borrowing;
import pl.edu.pg.booksharing.Booksharing.model.DTO.BorrowingBook.BorrowingDto;
import pl.edu.pg.booksharing.Booksharing.service.BorrowingService;

import javax.validation.Valid;

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
}
