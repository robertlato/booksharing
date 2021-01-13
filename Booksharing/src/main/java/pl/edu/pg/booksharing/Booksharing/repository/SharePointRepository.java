package pl.edu.pg.booksharing.Booksharing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pg.booksharing.Booksharing.model.SharePoint;

@Repository
public interface SharePointRepository extends JpaRepository<SharePoint, Long> {
    SharePoint findById(long id);
}
