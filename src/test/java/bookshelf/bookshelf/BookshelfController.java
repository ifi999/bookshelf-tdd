package bookshelf.bookshelf;

import bookshelf.booshelf.dto.CreateBookshelfResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
public class BookshelfController {

    @PostMapping("/bookshelf")
    public ResponseEntity<CreateBookshelfResponse> createBookshelf() {
        return ResponseEntity.created(URI.create("")).body(null);
    }

}
