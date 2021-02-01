package pl.edu.pg.booksharing.Booksharing.model.DTO.BorrowingInfo;


import pl.edu.pg.booksharing.Booksharing.model.DTO.BasicInfo.BookBasicInfoDto;

import java.sql.Timestamp;

public class BorrowingListInfoDto {

    // borrow the book
    //@NotBlank
    private java.sql.Timestamp checkOutDate;

    // return of the book
    private java.sql.Timestamp checkInDate;

    private BorrowedBookInfoDto book;

    public BorrowingListInfoDto() {
    }

    public BorrowingListInfoDto(Timestamp checkOutDate, Timestamp checkInDate, BorrowedBookInfoDto book) {
        this.checkOutDate = checkOutDate;
        this.checkInDate = checkInDate;
        this.book = book;
    }

    public Timestamp getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(Timestamp checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public Timestamp getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(Timestamp checkInDate) {
        this.checkInDate = checkInDate;
    }

    public BorrowedBookInfoDto getBook() {
        return book;
    }

    public void setBook(BorrowedBookInfoDto book) {
        this.book = book;
    }
}
