package bookshelf.book;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class BookService {


    public CreateBookResponse createBook(final Long id, final CreateBookRequest request) {
        return null;
    }
}
