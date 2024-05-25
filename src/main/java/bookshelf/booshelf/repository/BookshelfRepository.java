package bookshelf.booshelf.repository;

import bookshelf.booshelf.entity.Bookshelf;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookshelfRepository extends JpaRepository<Bookshelf, Long> {

    Optional<Bookshelf> findBookshelfByName(String name);

}
