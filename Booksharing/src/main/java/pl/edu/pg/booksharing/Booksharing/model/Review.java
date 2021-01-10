package pl.edu.pg.booksharing.Booksharing.model;

import javax.persistence.*;

@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String review;
    private java.sql.Timestamp creationDate;

    @ManyToOne
    @JoinColumn(name = "BookID")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "UserID")
    private User user;
}
