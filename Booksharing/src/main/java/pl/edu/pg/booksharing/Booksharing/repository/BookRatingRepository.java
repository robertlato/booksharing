package pl.edu.pg.booksharing.Booksharing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import pl.edu.pg.booksharing.Booksharing.model.BookRating;

@Repository
public interface BookRatingRepository extends JpaRepository<BookRating, Long>, JpaSpecificationExecutor<BookRating> {
}
