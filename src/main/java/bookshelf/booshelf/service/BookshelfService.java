package bookshelf.booshelf.service;

import bookshelf.booshelf.dto.CreateBookshelfRequest;
import bookshelf.booshelf.dto.CreateBookshelfResponse;
import bookshelf.booshelf.dto.GetBookshelfResponse;
import bookshelf.booshelf.dto.UpdateBookshelfRequest;
import bookshelf.booshelf.entity.Bookshelf;
import bookshelf.booshelf.repository.BookshelfRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Transactional
@Service
public class BookshelfService {

    private final BookshelfRepository bookshelfRepository;

    public BookshelfService(BookshelfRepository bookshelfRepository) {
        this.bookshelfRepository = bookshelfRepository;
    }

    public CreateBookshelfResponse createBookshelf(final CreateBookshelfRequest request) {
        final String bookshelfName = request.getName();
        final Bookshelf bookshelf = new Bookshelf(bookshelfName, request.getFloor());

        checkIfBookshelfExists(bookshelfName);

        final Bookshelf savedBookshelf = bookshelfRepository.save(bookshelf);

        return new CreateBookshelfResponse(savedBookshelf);
    }

    public GetBookshelfResponse getBookshelf(final Long id) {
        final Bookshelf bookshelf = bookshelfRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Failed to get bookshelf. Invalid bookshelf id: " + id));

        return new GetBookshelfResponse(bookshelf);
    }

    public void updateBookshelf(final UpdateBookshelfRequest request, final Long id) {
        final Bookshelf bookshelf = bookshelfRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Failed to update bookshelf. Invalid bookshelf id: " + id));

        bookshelf.updateBookshelfDetails(request.getName(), request.getFloor());
    }

    public void deleteBookshelf(final Long id) {
        bookshelfRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Failed to get bookshelf. Invalid bookshelf id: " + id));
        bookshelfRepository.deleteById(id);
    }

    private void checkIfBookshelfExists(final String bookshelfName) {
        final Optional<Bookshelf> existingBookshelf = bookshelfRepository.findBookshelfByName(bookshelfName);
        if (existingBookshelf.isPresent()) {
            throw new EntityExistsException("A bookshelf with the name '" + bookshelfName + "' already exists.");
        }
    }

}
