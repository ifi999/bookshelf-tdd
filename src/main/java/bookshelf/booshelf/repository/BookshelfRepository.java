package bookshelf.booshelf.repository;

import bookshelf.booshelf.entity.Bookshelf;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookshelfRepository extends JpaRepository<Bookshelf, Long> {
}
