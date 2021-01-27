package pl.edu.pg.booksharing.Booksharing.service;

import pl.edu.pg.booksharing.Booksharing.exception.ResourceNotFoundException;
import pl.edu.pg.booksharing.Booksharing.model.Author;
import pl.edu.pg.booksharing.Booksharing.model.DTO.AuthorsList.AuthorInfoDto;

import java.util.List;

public interface AuthorService {

    Author save(Author author);

    List<Author> findAll();

    Author findByLastName(String lastName) throws ResourceNotFoundException;

    Author convertToEntity(AuthorInfoDto authorInfoDto);

    AuthorInfoDto convertToDto(Author author);


}
