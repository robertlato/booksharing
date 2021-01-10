package pl.edu.pg.booksharing.Booksharing.model;


import javax.persistence.*;

@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    private String country;
    private String city;
    private String postalCode;
    private String Street;
    private String houseNumber;

    @OneToOne(mappedBy = "address")
    private SharePoint sharePoint;

    @OneToOne(mappedBy = "address")
    private Publisher publisher;
}
