package bookshelf.book;

import bookshelf.book.dto.CreateBookRequest;
import bookshelf.book.dto.CreateBookResponse;
import bookshelf.book.entity.BookCategory;
import bookshelf.book.service.BookService;
import bookshelf.booshelf.entity.Bookshelf;
import bookshelf.booshelf.repository.BookshelfRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
public class BookServiceTest {

    @Autowired
    private BookService bookService;

    @Autowired
    private BookshelfRepository bookshelfRepository;

    @Test
    void 책을_생성한다() {
        // given
        final Bookshelf 이케아_책장 = bookshelfRepository.save(new Bookshelf("이케아 5단 책장", 5));

        final CreateBookRequest 객사오_생성_요청 = new CreateBookRequest(
                "객체지향의 사실과 오해",
                "조영호",
                "9788998139766",
                LocalDate.of(2023, 6, 1),
                BookCategory.IT
        );

        // when
        final CreateBookResponse 객사오_생성_응답 = bookService.createBook(이케아_책장.getId(), 객사오_생성_요청);

        // then
        assertThat(객사오_생성_응답.getId()).isNotNull();
        assertThat("객체지향의 사실과 오해").isEqualTo(객사오_생성_응답.getTitle());
    }

}
