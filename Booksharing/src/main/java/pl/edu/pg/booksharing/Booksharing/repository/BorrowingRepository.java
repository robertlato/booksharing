package pl.edu.pg.booksharing.Booksharing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import pl.edu.pg.booksharing.Booksharing.model.Borrowing;

public interface BorrowingRepository extends JpaRepository<Borrowing,Long>, JpaSpecificationExecutor<Borrowing> {
}
