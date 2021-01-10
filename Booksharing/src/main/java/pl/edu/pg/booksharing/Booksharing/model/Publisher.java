package pl.edu.pg.booksharing.Booksharing.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Publisher {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String shortName;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "AddressID", referencedColumnName = "id")
    private Address address;

    @OneToMany(mappedBy = "publisher")
    @JsonIgnore
    private List<Book> books = new ArrayList<>();

}
