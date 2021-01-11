package pl.edu.pg.booksharing.Booksharing.service;

import pl.edu.pg.booksharing.Booksharing.model.BookCopy;

import java.util.List;

public interface BookCopyService {

    void save(BookCopy bookCopy);

    List<BookCopy> findAll();
}
