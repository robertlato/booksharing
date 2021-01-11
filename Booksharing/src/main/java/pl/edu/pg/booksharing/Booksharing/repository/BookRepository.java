package pl.edu.pg.booksharing.Booksharing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pg.booksharing.Booksharing.model.Book;


@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Book findById(long id);
}
