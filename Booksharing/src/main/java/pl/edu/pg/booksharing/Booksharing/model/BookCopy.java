package pl.edu.pg.booksharing.Booksharing.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;


@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
@Entity
public class BookCopy {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private boolean isBorrowed;



    @NotNull
    @ManyToOne
    @JoinColumn(name = "BookID")
    private Book book;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "SharePointID")
    private SharePoint sharePoint;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bookCopy")
    @JsonIgnore
    private List<Borrowing> borrowings = new ArrayList<>();



    public BookCopy() {
    }

    public BookCopy(Book book, SharePoint sharePoint) {
        this.isBorrowed = false;
        this.book = book;
        this.sharePoint = sharePoint;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isBorrowed() {
        return isBorrowed;
    }

    public void setBorrowed(boolean borrowed) {
        isBorrowed = borrowed;
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

    public List<Borrowing> getBorrowings() {
        return borrowings;
    }

    public void setBorrowings(List<Borrowing> borrowings) {
        this.borrowings = borrowings;
    }
}
