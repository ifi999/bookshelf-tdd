package bookshelf.bookshelf;

import bookshelf.booshelf.dto.CreateBookshelfRequest;
import bookshelf.booshelf.dto.CreateBookshelfResponse;
import bookshelf.booshelf.service.BookshelfService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
public class BookshelfController {

    private final BookshelfService bookshelfService;

    public BookshelfController(final BookshelfService bookshelfService) {
        this.bookshelfService = bookshelfService;
    }

    @PostMapping("/bookshelf")
    public ResponseEntity<CreateBookshelfResponse> createBookshelf(final @RequestBody CreateBookshelfRequest request) {
        final CreateBookshelfResponse bookshelf = bookshelfService.createBookshelf(request);

        return ResponseEntity.created(URI.create("/bookshelf/" + bookshelf.getId())).body(bookshelf);
    }

}
