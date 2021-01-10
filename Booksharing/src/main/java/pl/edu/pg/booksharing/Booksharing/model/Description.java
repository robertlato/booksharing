package pl.edu.pg.booksharing.Booksharing.model;

import javax.persistence.*;

@Entity
public class Description {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    @JoinColumn(name = "BookID", referencedColumnName = "id")
    private Book book;

    private String description;

    @OneToOne
    @JoinColumn(name = "UserID", referencedColumnName = "id")
    private User user;
}
