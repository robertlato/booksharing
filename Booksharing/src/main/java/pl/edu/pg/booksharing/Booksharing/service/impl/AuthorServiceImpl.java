package pl.edu.pg.booksharing.Booksharing.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pg.booksharing.Booksharing.exception.ResourceNotFoundException;
import pl.edu.pg.booksharing.Booksharing.model.Author;
import pl.edu.pg.booksharing.Booksharing.model.Book;
import pl.edu.pg.booksharing.Booksharing.model.DTO.AuthorsList.AuthorInfoDto;
import pl.edu.pg.booksharing.Booksharing.model.DTO.BasicInfo.BookBasicInfoDto;
import pl.edu.pg.booksharing.Booksharing.repository.AuthorRepository;
import pl.edu.pg.booksharing.Booksharing.service.AuthorService;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    private AuthorRepository authorRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Author save(Author author) {
        if(authorRepository.findByLastName(author.getLastName()) != null) {

        } else {
            authorRepository.save(author);
        }
        return author;
    }

    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Override
    public Author findByLastName(String lastName) throws ResourceNotFoundException {
        Author author = authorRepository.findByLastName(lastName);
        if (author == null) {
            throw new ResourceNotFoundException("Author with name " + lastName + "does not exist");
        } else {
            return author;
        }
    }

    @Override
    public Author convertToEntity(AuthorInfoDto authorInfoDto) {
        Author author = modelMapper.map(authorInfoDto, Author.class);

        return author;
    }

    @Override
    public AuthorInfoDto convertToDto(Author author) {
        AuthorInfoDto authorInfoDto = modelMapper.map(author, AuthorInfoDto.class);

        return authorInfoDto;
    }


}
