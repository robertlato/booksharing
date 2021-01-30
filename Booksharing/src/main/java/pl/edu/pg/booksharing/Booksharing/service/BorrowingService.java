package pl.edu.pg.booksharing.Booksharing.service;

import pl.edu.pg.booksharing.Booksharing.exception.BorrowedAlreadyException;
import pl.edu.pg.booksharing.Booksharing.exception.ResourceNotFoundException;
import pl.edu.pg.booksharing.Booksharing.model.Borrowing;
import pl.edu.pg.booksharing.Booksharing.model.DTO.BorrowingBook.BorrowingDto;

public interface BorrowingService {

    void borrow(Borrowing borrowing) throws ResourceNotFoundException, BorrowedAlreadyException;

    Borrowing convertToEntity(BorrowingDto borrowingDto) throws ResourceNotFoundException;
}
