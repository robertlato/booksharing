package pl.edu.pg.booksharing.Booksharing.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pg.booksharing.Booksharing.exception.ResourceNotFoundException;
import pl.edu.pg.booksharing.Booksharing.model.DTO.PublishersList.PublisherInfoDto;
import pl.edu.pg.booksharing.Booksharing.model.Publisher;
import pl.edu.pg.booksharing.Booksharing.service.PublisherService;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins={ "http://localhost:8889", "http://localhost:3000" }, maxAge = 3600, allowedHeaders = "*")
@RestController
public class PublisherController {

    private PublisherService publisherService;

    @Autowired
    public PublisherController(PublisherService publisherService){this.publisherService = publisherService;}

    @Autowired
    ModelMapper modelMapper;

    // get all publishers
    @GetMapping(path = "api/publishers")
    public List<PublisherInfoDto> getAllPublishers() {
        List<Publisher> publishers = publisherService.findAll();

        return publishers.stream().map(publisher -> modelMapper.map(publisher, PublisherInfoDto.class)).collect(Collectors.toList());
    }

    // get publisher by name
    @GetMapping( path = "/api/publisher/{name}")
    public PublisherInfoDto getPublisherByName(@PathVariable String name) throws ResourceNotFoundException {
        return publisherService.convertToDto(publisherService.findByName(name));
    }
}
