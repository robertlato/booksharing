package pl.edu.pg.booksharing.Booksharing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import pl.edu.pg.booksharing.Booksharing.model.Borrowing;

@Repository
public interface BorrowingRepository extends JpaRepository<Borrowing,Long>{
    Borrowing findById(long id);
}
