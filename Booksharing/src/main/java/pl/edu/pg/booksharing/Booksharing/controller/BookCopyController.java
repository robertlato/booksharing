package pl.edu.pg.booksharing.Booksharing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pg.booksharing.Booksharing.model.BookCopy;
import pl.edu.pg.booksharing.Booksharing.service.BookCopyService;

import javax.validation.Valid;
import java.util.List;

@RestController
public class BookCopyController {

    @Autowired
    BookCopyService bookCopyService;

    public BookCopyController() {
    }


    // add new copy of the book
    @PostMapping(path = "/api/bookcopy")
    public void addBookCopy(@Valid @RequestBody BookCopy bookCopy) {
        bookCopyService.save(bookCopy);
    }

    // get all book copies
    @GetMapping(path = "api/bookcopies")
    public List<BookCopy> getAllBookCopies(){
        return bookCopyService.findAll();
    }
}
