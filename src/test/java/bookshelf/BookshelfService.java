package bookshelf;

import org.springframework.stereotype.Service;

@Service
public class BookshelfService {

    public CreateBookshelfResponse addBookshelf(final CreateBookshelfRequest request) {
        return new CreateBookshelfResponse(null, "이케아 5단 책장", 5);
    }

}
