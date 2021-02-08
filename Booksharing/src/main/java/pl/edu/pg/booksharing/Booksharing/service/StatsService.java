package pl.edu.pg.booksharing.Booksharing.service;

import pl.edu.pg.booksharing.Booksharing.exception.ResourceNotFoundException;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public interface StatsService {

    int getNumberOfBorrowingsPerCity(String city);

    String getMostPopularSharePoint();

    LinkedHashMap<String, Integer> getMostPopularBooks() throws ResourceNotFoundException;
}
