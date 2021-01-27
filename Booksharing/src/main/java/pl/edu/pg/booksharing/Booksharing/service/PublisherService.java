package pl.edu.pg.booksharing.Booksharing.service;

import pl.edu.pg.booksharing.Booksharing.exception.ResourceNotFoundException;
import pl.edu.pg.booksharing.Booksharing.model.DTO.PublishersList.PublisherInfoDto;
import pl.edu.pg.booksharing.Booksharing.model.Publisher;

import java.util.List;

public interface PublisherService {

    Publisher findByName(String name) throws ResourceNotFoundException;

    List<Publisher> findAll();

    PublisherInfoDto convertToDto(Publisher publisher);
    Publisher convertToEntity(PublisherInfoDto publisherInfoDto);
}
