package bookshelf.book.service;

import bookshelf.book.dto.CreateBookRequest;
import bookshelf.book.dto.CreateBookResponse;
import bookshelf.book.dto.GetBookResponse;
import bookshelf.book.entity.Book;
import bookshelf.book.repository.BookRepository;
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

    public CreateBookResponse createBook(final Long bookShelfId, final CreateBookRequest request) {
        final Bookshelf bookshelf = bookshelfRepository.findById(bookShelfId).orElseThrow(
                () -> new EntityNotFoundException("Failed to get bookshelf. Invalid bookshelf id: " + bookShelfId)
        );

        final Book savedBook = bookRepository.save(request.toEntity());

        bookshelf.addBook(savedBook);

        return new CreateBookResponse(savedBook);
    }

    public GetBookResponse getBook(final Long id) {
        return null;
    }
}
