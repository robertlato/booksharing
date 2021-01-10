package pl.edu.pg.booksharing.Booksharing.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String firstName;
    private String lastName;
    private String secondName;

    @ManyToMany
    private List<Book> books = new ArrayList<>();
}
