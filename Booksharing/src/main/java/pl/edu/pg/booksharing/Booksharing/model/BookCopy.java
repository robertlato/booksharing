package pl.edu.pg.booksharing.Booksharing.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class BookCopy {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "BookID")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "SharePointID")
    private SharePoint sharePoint;

    private boolean isBorrowed;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bookCopy")
    @JsonIgnore
    private List<Borrowing> borrowings = new ArrayList<>();
}
