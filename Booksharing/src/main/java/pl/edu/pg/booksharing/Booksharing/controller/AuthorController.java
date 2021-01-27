package pl.edu.pg.booksharing.Booksharing.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.edu.pg.booksharing.Booksharing.exception.ResourceNotFoundException;
import pl.edu.pg.booksharing.Booksharing.model.Author;
import pl.edu.pg.booksharing.Booksharing.model.DTO.AuthorsList.AuthorInfoDto;
import pl.edu.pg.booksharing.Booksharing.service.AuthorService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins={ "http://localhost:8889", "http://localhost:3000" }, maxAge = 3600, allowedHeaders = "*")
@RestController
public class AuthorController {

    private AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) { this.authorService = authorService;}

    @Autowired
    ModelMapper modelMapper;

    // add new author
    @PostMapping(path = "/api/author")
    public AuthorInfoDto addAuthorDto(@Valid @RequestBody AuthorInfoDto authorInfoDto) {
        Author author = authorService.convertToEntity(authorInfoDto);
        Author authorAdded = authorService.save(author);
        return authorService.convertToDto(authorAdded);
    }


    // get all Authors
    @GetMapping(path = "api/authors")
    public List<AuthorInfoDto> getAllAuthors() {
        List<Author> authors = authorService.findAll();

        return authors.stream().map(author -> modelMapper.map(author, AuthorInfoDto.class)).collect(Collectors.toList());
    }


    // get author by last name
    @GetMapping(path = "/api/author/{lastName}")
    public AuthorInfoDto getAuthorByLastName(@PathVariable String lastName) throws ResourceNotFoundException {
        return authorService.convertToDto(authorService.findByLastName(lastName));
    }

}
