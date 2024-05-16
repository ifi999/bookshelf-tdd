package bookshelf;

import org.springframework.stereotype.Service;

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

    public GetBookshelfResponse getBookshelf(Long id) {
        final Bookshelf bookshelf = bookshelfRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid bookshelf id: " + id));

        return new GetBookshelfResponse(bookshelf);
    }

}
