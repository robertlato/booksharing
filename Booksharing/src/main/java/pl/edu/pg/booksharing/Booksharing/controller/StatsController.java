package pl.edu.pg.booksharing.Booksharing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pg.booksharing.Booksharing.service.StatsService;

@CrossOrigin(origins={ "http://localhost:8889", "http://localhost:3000" }, maxAge = 3600, allowedHeaders = "*")
@RestController
public class StatsController {

    StatsService statsService;

    @Autowired
    public StatsController(StatsService statsService) {
        this.statsService = statsService;
    }

    @GetMapping(path = "/api/stats/city/{city}")
    public int getNumberOfBorrowingsPerCity(@PathVariable String city) {
        return statsService.getNumberOfBorrowingsPerCity(city);
    }
}
