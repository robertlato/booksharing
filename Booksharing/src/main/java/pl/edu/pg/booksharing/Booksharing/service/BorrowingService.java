package pl.edu.pg.booksharing.Booksharing.service;

import pl.edu.pg.booksharing.Booksharing.exception.BorrowedAlreadyException;
import pl.edu.pg.booksharing.Booksharing.exception.ResourceNotFoundException;
import pl.edu.pg.booksharing.Booksharing.model.Borrowing;
import pl.edu.pg.booksharing.Booksharing.model.DTO.BorrowingInfo.BorrowingListInfoDto;
import pl.edu.pg.booksharing.Booksharing.model.DTO.BorrowingBook.BorrowingDto;
import pl.edu.pg.booksharing.Booksharing.model.DTO.BorrowingBook.BorrowingReturnDto;

import java.util.List;

public interface BorrowingService {

    void borrow(Borrowing borrowing) throws ResourceNotFoundException, BorrowedAlreadyException;

    void returnBook(long id) throws ResourceNotFoundException;

    Borrowing findById(long id);

    Borrowing convertToEntity(BorrowingDto borrowingDto) throws ResourceNotFoundException;

    List<BorrowingListInfoDto> findAllBorrowings();
}
