package pl.edu.pg.booksharing.Booksharing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.edu.pg.booksharing.Booksharing.model.BookCopy;
import pl.edu.pg.booksharing.Booksharing.service.BookCopyService;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins={ "http://localhost:8889", "http://localhost:3000" }, maxAge = 3600, allowedHeaders = "*")
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
