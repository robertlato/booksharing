package pl.edu.pg.booksharing.Booksharing.model;

import javax.persistence.*;

@Entity
public class Borrowing {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "BookCopyID")
    private BookCopy bookCopy;

    @ManyToOne
    @JoinColumn(name = "SharePointID")
    private SharePoint sharePoint;

    @ManyToOne
    private User user;

    // borrow the book
    private java.sql.Timestamp checkOutDate;

    // return of the book
    private java.sql.Timestamp checkInDate;

}
