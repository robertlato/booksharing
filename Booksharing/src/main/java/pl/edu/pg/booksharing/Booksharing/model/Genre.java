package pl.edu.pg.booksharing.Booksharing.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(mappedBy = "genre")
    @JsonIgnore
    private List<Book> books = new ArrayList<>();

    private String name;
}
