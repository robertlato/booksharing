package pl.edu.pg.booksharing.Booksharing.component;

import org.springframework.security.core.Authentication;


// return information about current authenticated user

public interface IAuthenticationFacade {
    Authentication getAuthentication();
}
