package pl.edu.pg.booksharing.Booksharing.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import pl.edu.pg.booksharing.Booksharing.exception.ResourceNotFoundException;
import pl.edu.pg.booksharing.Booksharing.model.Author;
import pl.edu.pg.booksharing.Booksharing.repository.AuthorRepository;
import pl.edu.pg.booksharing.Booksharing.service.AuthorService;

import java.util.List;

public class AuthorServiceImpl implements AuthorService {

    private AuthorRepository authorRepository;

    //@Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {this.authorRepository = authorRepository;}

    @Override
    public void save(Author author) {
        if(authorRepository.findByLastName(author.getLastName()) != null) {

        } else {
            authorRepository.save(author);
        }
    }

    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Override
    public void findByLastName(String lastName) throws ResourceNotFoundException {
        Author author = authorRepository.findByLastName(lastName);
        if (author == null) {
            throw new ResourceNotFoundException("Author with name " + lastName + "does not exist");
        } else {
            authorRepository.findByLastName(lastName);
        }
    }
}
