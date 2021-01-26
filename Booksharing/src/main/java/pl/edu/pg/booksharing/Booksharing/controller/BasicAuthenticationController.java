package pl.edu.pg.booksharing.Booksharing.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pg.booksharing.Booksharing.config.AuthenticationBean;

@CrossOrigin(origins={ "http://localhost:8889", "http://localhost:3000" }, maxAge = 3600, allowedHeaders = "*")
@RestController
public class BasicAuthenticationController {

    @GetMapping(path = "/basicauth")
    public AuthenticationBean authBean() {
        //throw new RuntimeException("Some Error has Happened! Contact Support at ***-***");
        return new AuthenticationBean("You are authenticated");
    }
}

