package bookshelf.book;

import bookshelf.booshelf.entity.Bookshelf;
import bookshelf.booshelf.repository.BookshelfRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Transactional
@Service
public class BookService {

    private final BookshelfRepository bookshelfRepository;

    private final BookRepository bookRepository;

    public BookService(final BookshelfRepository bookshelfRepository, final BookRepository bookRepository) {
        this.bookshelfRepository = bookshelfRepository;
        this.bookRepository = bookRepository;
    }

    public CreateBookResponse createBook(final Long id, final CreateBookRequest request) {
        final Bookshelf bookshelf = bookshelfRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Failed to get bookshelf. Invalid bookshelf id: " + id));

        final Book book = request.toEntity();
        final Book savedBook = bookRepository.save(book);

        bookshelf.addBook(savedBook);

        return new CreateBookResponse(savedBook);
    }
}
