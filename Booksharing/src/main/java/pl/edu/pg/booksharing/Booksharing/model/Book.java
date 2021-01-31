package pl.edu.pg.booksharing.Booksharing.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private boolean isBorrowed;

//    @DateTimeFormat
    private java.sql.Date releaseDate;

    @NotBlank
    private String title;

    @NotBlank
    @Size(min = 12, max = 13)
    private String isbn;



    @NotNull
    @ManyToOne
    @JoinColumn(name = "SharePointID")
    private SharePoint sharePoint;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "book")
    @JsonIgnore
    private List<Borrowing> borrowings = new ArrayList<>();


    @NotNull
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "PublisherID")
    private Publisher publisher;

    @NotNull
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<Author> authors = new ArrayList<>();

    @NotNull
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "GenreID")
    private Genre genre;

    @OneToOne(mappedBy = "book")
    private Description description;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "book")
    private List<BookRating> bookRatings = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "book")
    private List<Review> reviews = new ArrayList<>();



    public Book() {
    }

    public Book(Date releaseDate, String title, @Size(min = 13, max = 13) String isbn, Publisher publisher,
                List<Author> authors, Genre genre, SharePoint sharePoint) {
        this.isBorrowed = false;
        this.releaseDate = releaseDate;
        this.title = title;
        this.isbn = isbn;
        this.publisher = publisher;
        this.authors = authors;
        this.genre = genre;
        this.sharePoint = sharePoint;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

//    public List<BookCopy> getBookCopies() {
//        return bookCopies;
//    }
//
//    public void setBookCopies(List<BookCopy> bookCopies) {
//        this.bookCopies = bookCopies;
//    }

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

    public boolean isBorrowed() {
        return isBorrowed;
    }

    public void setBorrowed(boolean borrowed) {
        isBorrowed = borrowed;
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
