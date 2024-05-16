package bookshelf;

import org.springframework.stereotype.Service;

@Service
public class BookshelfService {

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
        return new GetBookshelfResponse(id, "이케아 5단 책장", 5);
    }

}
