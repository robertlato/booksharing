package pl.edu.pg.booksharing.Booksharing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pg.booksharing.Booksharing.model.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    Author findByLastName(String lastName);
}
