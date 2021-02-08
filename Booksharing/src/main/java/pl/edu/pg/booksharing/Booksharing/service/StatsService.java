package pl.edu.pg.booksharing.Booksharing.service;

import java.util.List;

public interface StatsService {

    int getNumberOfBorrowingsPerCity(String city);

    String getMostPopularSharePoint();

    List<String> getMostPopularBooks(String isbn);
}
