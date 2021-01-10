package pl.edu.pg.booksharing.Booksharing.model;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
public class BookRating {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "BookID")
    private Book book;

    @Min(1)
    @Max(5)
    private int rating;

    @ManyToOne
    @JoinColumn(name = "UserID")
    private User user;

}
