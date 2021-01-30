package pl.edu.pg.booksharing.Booksharing.model.DTO.BorrowingBook;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

public class BorrowingDto {

    private java.sql.Timestamp checkOutDate;

    private java.sql.Timestamp checkInDate;

    private BookBorrowingDto book;

    private SharePointBorrowingDto sharePoint;

    private UserBorrowingDto user;

    public BorrowingDto() {
    }

    public BorrowingDto(Timestamp checkOutDate, Timestamp checkInDate, BookBorrowingDto book, SharePointBorrowingDto sharePoint, UserBorrowingDto user) {
        this.checkOutDate = checkOutDate;
        this.checkInDate = checkInDate;
        this.book = book;
        this.sharePoint = sharePoint;
        this.user = user;
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
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MONTH, 1);
        Timestamp timestamp = new Timestamp(calendar.getTimeInMillis());
        this.checkInDate = timestamp;

        return checkInDate;
    }

    public void setCheckInDate(Timestamp checkInDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MONTH, 1);
        Timestamp timestamp = new Timestamp(calendar.getTimeInMillis());
        this.checkInDate = timestamp;
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

    public UserBorrowingDto getUser() {
        return user;
    }

    public void setUser(UserBorrowingDto user) {
        this.user = user;
    }
}
