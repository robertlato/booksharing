package pl.edu.pg.booksharing.Booksharing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pg.booksharing.Booksharing.model.Book;


@Repository
public interface BookRepository extends JpaRepository<Book, Long>, JpaSpecificationExecutor<Book>, CrudRepository<Book, Long> {
    Book findById(long id);

    Book findByIsbn(String isbn);

}
