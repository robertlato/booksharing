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
public class SharePoint {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "UserID")
    private User user;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "AddressID", referencedColumnName = "id")
    private Address address;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sharePoint")
//    @JsonIgnore
    private List<Book> books = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sharePoint")
    @JsonIgnore
    private List<Borrowing> borrowings = new ArrayList<>();




    public SharePoint() {
    }

    public SharePoint(@NotNull Address address) {
        this.address = address;
    }

    public SharePoint(User user, Address address) {
        this.user = user;
        this.address = address;
    }

    public SharePoint(Long id, @NotNull User user, @NotNull Address address, List<Book> books, List<Borrowing> borrowings) {
        this.id = id;
        this.user = user;
        this.address = address;
        this.books = books;
        this.borrowings = borrowings;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }


    public List<Borrowing> getBorrowings() {
        return borrowings;
    }

    public void setBorrowings(List<Borrowing> borrowings) {
        this.borrowings = borrowings;
    }
}
