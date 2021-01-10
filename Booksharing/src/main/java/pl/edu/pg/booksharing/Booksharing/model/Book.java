package pl.edu.pg.booksharing.Booksharing.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "book")
    @JsonIgnore
    private List<BookCopy> bookCopies = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "PublisherID")
    private Publisher publisher;

    @ManyToMany
    private List<Author> authors = new ArrayList<>();

    private String title;
    @Size(min = 13, max = 13)
    private String ISBN;

    @ManyToOne
    @JoinColumn(name = "GenreID")
    private Genre genre;

    private java.sql.Timestamp releaseDate;

    @OneToOne(mappedBy = "book")
    private Description description;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "book")
    @JsonIgnore
    private List<BookRating> bookRatings = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "book")
    @JsonIgnore
    private List<Review> reviews = new ArrayList<>();


}
