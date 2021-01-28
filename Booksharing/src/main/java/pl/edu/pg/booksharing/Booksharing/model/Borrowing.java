package pl.edu.pg.booksharing.Booksharing.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;


@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
@Entity
public class Borrowing {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // borrow the book
    @NotBlank
    private java.sql.Timestamp checkOutDate;

    // return of the book
    private java.sql.Timestamp checkInDate;




    @NotNull
    @ManyToOne
    @JoinColumn(name = "BookID")
    private Book book;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "SharePointID")
    private SharePoint sharePoint;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "UserID")
    private User user;



    public Borrowing() {
    }

    public Borrowing(Timestamp checkOutDate, Timestamp checkInDate, Book book, SharePoint sharePoint, User user) {
        this.checkOutDate = checkOutDate;
        this.checkInDate = checkInDate;
        this.book = book;
        this.sharePoint = sharePoint;
        this.user = user;
    }




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public SharePoint getSharePoint() {
        return sharePoint;
    }

    public void setSharePoint(SharePoint sharePoint) {
        this.sharePoint = sharePoint;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
