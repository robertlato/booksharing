package pl.edu.pg.booksharing.Booksharing.model;

import java.util.ArrayList;
import java.util.List;

public class Stats {

    private int numberOfBorrowingsPerCity;

    private String mostPopularSharePoint;

    List<String> mostPopularBooks = new ArrayList<>();

    public Stats() {
    }

    public Stats(int numberOfBorrowingsPerCity, String mostPopularSharePoint, List<String> mostPopularBooks) {
        this.numberOfBorrowingsPerCity = numberOfBorrowingsPerCity;
        this.mostPopularSharePoint = mostPopularSharePoint;
        this.mostPopularBooks = mostPopularBooks;
    }

    public int getNumberOfBorrowingsPerCity() {
        return numberOfBorrowingsPerCity;
    }

    public void setNumberOfBorrowingsPerCity(int numberOfBorrowingsPerCity) {
        this.numberOfBorrowingsPerCity = numberOfBorrowingsPerCity;
    }

    public String getMostPopularSharePoint() {
        return mostPopularSharePoint;
    }

    public void setMostPopularSharePoint(String mostPopularSharePoint) {
        this.mostPopularSharePoint = mostPopularSharePoint;
    }

    public List<String> getMostPopularBooks() {
        return mostPopularBooks;
    }

    public void setMostPopularBooks(List<String> mostPopularBooks) {
        this.mostPopularBooks = mostPopularBooks;
    }
}
