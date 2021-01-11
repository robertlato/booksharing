package pl.edu.pg.booksharing.Booksharing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pg.booksharing.Booksharing.model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
}
