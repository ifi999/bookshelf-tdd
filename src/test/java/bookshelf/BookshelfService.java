package bookshelf;

import org.springframework.stereotype.Service;

@Service
public class BookshelfService {

    public CreateBookshelfResponse addBookshelf(final CreateBookshelfRequest request) {
        final Bookshelf bookshelf = new Bookshelf(request.getName(), request.getFloor());

        return new CreateBookshelfResponse(bookshelf);
    }

}
