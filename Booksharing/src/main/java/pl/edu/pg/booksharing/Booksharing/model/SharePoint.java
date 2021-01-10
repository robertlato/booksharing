package pl.edu.pg.booksharing.Booksharing.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class SharePoint {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "UserID")
    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "AddressID", referencedColumnName = "id")
    private Address address;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sharePoint")
    @JsonIgnore
    private List<BookCopy> bookCopies = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sharePoint")
    @JsonIgnore
    private List<Borrowing> borrowings = new ArrayList<>();
}
