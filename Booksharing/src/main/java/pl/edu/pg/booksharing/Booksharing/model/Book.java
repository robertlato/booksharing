package pl.edu.pg.booksharing.Booksharing.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    private java.sql.Timestamp releaseDate;

    @NotBlank
    private String title;

    @NotBlank
    @Size(min = 13, max = 13)
    private String ISBN;



    @OneToMany(cascade = CascadeType.ALL, mappedBy = "book")
    @JsonIgnore
    private List<BookCopy> bookCopies = new ArrayList<>();

    @NotNull
    @ManyToOne
    @JoinColumn(name = "PublisherID")
    private Publisher publisher;

    @NotBlank
    @ManyToMany
    private List<Author> authors = new ArrayList<>();

    @NotNull
    @ManyToOne
    @JoinColumn(name = "GenreID")
    private Genre genre;

    @OneToOne(mappedBy = "book")
    private Description description;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "book")
    @JsonIgnore
    private List<BookRating> bookRatings = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "book")
    @JsonIgnore
    private List<Review> reviews = new ArrayList<>();



    public Book() {
    }

    public Book(Timestamp releaseDate, String title, @Size(min = 13, max = 13) String ISBN, Publisher publisher,
                List<Author> authors, Genre genre) {
        this.releaseDate = releaseDate;
        this.title = title;
        this.ISBN = ISBN;
        this.publisher = publisher;
        this.authors = authors;
        this.genre = genre;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Timestamp releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public List<BookCopy> getBookCopies() {
        return bookCopies;
    }

    public void setBookCopies(List<BookCopy> bookCopies) {
        this.bookCopies = bookCopies;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Description getDescription() {
        return description;
    }

    public void setDescription(Description description) {
        this.description = description;
    }

    public List<BookRating> getBookRatings() {
        return bookRatings;
    }

    public void setBookRatings(List<BookRating> bookRatings) {
        this.bookRatings = bookRatings;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }
}
