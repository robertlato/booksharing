package pl.edu.pg.booksharing.Booksharing.model;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import javax.persistence.*;


@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String country;
    private String city;
    private String postalCode;
    private String street;
    private String houseNumber;
    private Float lon;
    private Float lat;


    @JsonIgnore
    @OneToOne(mappedBy = "address")
    private SharePoint sharePoint;

    @JsonIgnore
    @OneToOne(mappedBy = "address")
    private Publisher publisher;




    public Address() {
    }

    public Address(String country, String city, String postalCode, String street, String houseNumber, Float lon, Float lat) {
        this.country = country;
        this.city = city;
        this.postalCode = postalCode;
        this.street = street;
        this.houseNumber = houseNumber;
        this.lon = lon;
        this.lat = lat;
    }




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }


    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public SharePoint getSharePoint() {
        return sharePoint;
    }

    public void setSharePoint(SharePoint sharePoint) {
        this.sharePoint = sharePoint;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Float getLon() {
        return lon;
    }

    public void setLon(float lon) {
        this.lon = lon;
    }

    public Float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }
}
