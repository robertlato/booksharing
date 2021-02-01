package pl.edu.pg.booksharing.Booksharing.model.DTO.BorrowingBook;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

public class BorrowingDto {

    private java.sql.Timestamp checkOutDate;

    private java.sql.Timestamp checkInDate;

    private BookBorrowingDto book;

    private SharePointBorrowingDto sharePoint;


    public BorrowingDto() {
    }

    public BorrowingDto(Timestamp checkOutDate, Timestamp checkInDate, BookBorrowingDto book, SharePointBorrowingDto sharePoint) {
        this.checkOutDate = checkOutDate;
        this.checkInDate = checkInDate;
        this.book = book;
        this.sharePoint = sharePoint;
    }


    //TODO NAPRAWIC USTAWIANIE W GECIE
    public Timestamp getCheckOutDate() {
        this.checkOutDate = new Timestamp(System.currentTimeMillis());

        return checkOutDate;
    }

    public void setCheckOutDate(Timestamp checkOutDate) {
        this.checkOutDate = new Timestamp(System.currentTimeMillis());
    }

    public Timestamp getCheckInDate() {
        return null;
    }

    public void setCheckInDate(Timestamp checkInDate) {
        this.checkInDate = null;
    }

    public BookBorrowingDto getBook() {
        return book;
    }

    public void setBook(BookBorrowingDto book) {
        this.book = book;
    }

    public SharePointBorrowingDto getSharePoint() {
        return sharePoint;
    }

    public void setSharePoint(SharePointBorrowingDto sharePoint) {
        this.sharePoint = sharePoint;
    }

}
