package pl.edu.pg.booksharing.Booksharing.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pg.booksharing.Booksharing.exception.ResourceNotFoundException;
import pl.edu.pg.booksharing.Booksharing.model.DTO.PublishersList.PublisherInfoDto;
import pl.edu.pg.booksharing.Booksharing.model.Publisher;

import pl.edu.pg.booksharing.Booksharing.repository.PublisherRepository;
import pl.edu.pg.booksharing.Booksharing.service.PublisherService;

import java.util.List;

@Service
public class PublisherServiceImpl implements PublisherService {

    private PublisherRepository publisherRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    public PublisherServiceImpl(PublisherRepository publisherRepository) {this.publisherRepository = publisherRepository;}

    @Override
    public Publisher findByName(String name) throws ResourceNotFoundException {
        Publisher publisher = publisherRepository.findByName(name);
        if (publisher == null) {
            throw new ResourceNotFoundException("Publisher not found.");
        } else {
            return publisher;
        }
    }


    @Override
    public List<Publisher> findAll() {
        return publisherRepository.findAll();
    }

    @Override
    public PublisherInfoDto convertToDto(Publisher publisher) {
        PublisherInfoDto publisherInfoDto = modelMapper.map(publisher, PublisherInfoDto.class);

        return publisherInfoDto;
    }

    @Override
    public Publisher convertToEntity(PublisherInfoDto publisherInfoDto) {
        Publisher publisher = modelMapper.map(publisherInfoDto, Publisher.class);

        return publisher;
    }
}
