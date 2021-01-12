package pl.edu.pg.booksharing.Booksharing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pg.booksharing.Booksharing.model.BookCopy;
import pl.edu.pg.booksharing.Booksharing.repository.BookCopyRepository;

import java.util.List;

@Service
public class BookCopyServiceImpl implements BookCopyService {

    @Autowired
    BookCopyRepository bookCopyRepository;

    @Override
    public void save(BookCopy bookCopy) {
        bookCopyRepository.save(bookCopy);
    }

    @Override
    public List<BookCopy> findAll() {
        return bookCopyRepository.findAll();
    }
}
