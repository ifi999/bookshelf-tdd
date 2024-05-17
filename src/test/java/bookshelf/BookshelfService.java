package bookshelf;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class BookshelfService {

    private final BookshelfRepository bookshelfRepository;

    public BookshelfService(BookshelfRepository bookshelfRepository) {
        this.bookshelfRepository = bookshelfRepository;
    }

    public CreateBookshelfResponse addBookshelf(final CreateBookshelfRequest request) {
        final Bookshelf bookshelf = new Bookshelf(request.getName(), request.getFloor());

        /**
         * 궁금한점
         * TDD 사이클대로라면 여기선 Repository를 만들 이유가 없어보임
         * 이건 무슨 기능 테스트를 진행해야 자연스럽게 Repository를 추가할 수 있을지?
         */

        return new CreateBookshelfResponse(bookshelf);
    }

    public GetBookshelfResponse getBookshelf(final Long id) {
        final Bookshelf bookshelf = bookshelfRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Failed to get bookshelf. Invalid bookshelf id: " + id));

        return new GetBookshelfResponse(bookshelf);
    }

    public void updateBookshelf(final UpdateBookshelfRequest request, final Long id) {
        final Bookshelf bookshelf = bookshelfRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Failed to update bookshelf. Invalid bookshelf id: " + id));

        bookshelf.updateBookshelfDetails(request.getName(), request.getFloor());
    }

    public void deleteBookshelf(final Long id) {
        bookshelfRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Failed to get bookshelf. Invalid bookshelf id: " + id));
        bookshelfRepository.deleteById(id);
    }

}
