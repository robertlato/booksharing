package pl.edu.pg.booksharing.Booksharing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pg.booksharing.Booksharing.model.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}