package bookshelf.bookshelf;

import bookshelf.booshelf.dto.CreateBookshelfRequest;
import bookshelf.booshelf.dto.CreateBookshelfResponse;
import bookshelf.booshelf.dto.GetBookshelfResponse;
import bookshelf.booshelf.dto.UpdateBookshelfRequest;
import bookshelf.booshelf.service.BookshelfService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/bookshelf/{id}")
    public ResponseEntity<GetBookshelfResponse> getBookshelf(
            final @PathVariable Long id
    ) {
        final GetBookshelfResponse bookshelf = bookshelfService.getBookshelf(id);

        return ResponseEntity.ok(bookshelf);
    }

    @PutMapping("/bookshelf/{id}")
    public ResponseEntity<Void> updateBookshelf(
            final @PathVariable Long id,
            final @RequestBody UpdateBookshelfRequest request
    ) {
        return ResponseEntity.ok().build();
    }

}
