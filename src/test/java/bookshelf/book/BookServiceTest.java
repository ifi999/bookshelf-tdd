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
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
        assertThat("조영호").isEqualTo(객사오_생성_응답.getAuthor());
        assertThat("9788998139766").isEqualTo(객사오_생성_응답.getIsbn());
        assertThat(LocalDate.of(2023, 6, 1)).isEqualTo(객사오_생성_응답.getPurchaseDate());
        assertThat(BookCategory.IT).isEqualTo(객사오_생성_응답.getBookCategory());
    }

    @Test
    void 책을_생성할_경우_도서명은_필수값이다() {
        // given
        final Bookshelf 이케아_책장 = bookshelfRepository.save(new Bookshelf("이케아 5단 책장", 5));

        final CreateBookRequest 도서명_null_생성_요청 = new CreateBookRequest(
                null,
                "조영호",
                "9788998139766",
                LocalDate.of(2023, 6, 1),
                BookCategory.IT
        );

        final CreateBookRequest 도서명_공백_생성_요청 = new CreateBookRequest(
                "",
                "조영호",
                "9788998139766",
                LocalDate.of(2023, 6, 1),
                BookCategory.IT
        );

        final CreateBookRequest 도서명_공백_여러_개_생성_요청 = new CreateBookRequest(
                "   ",
                "조영호",
                "9788998139766",
                LocalDate.of(2023, 6, 1),
                BookCategory.IT
        );

        // when

        // then
        assertThatThrownBy(() -> bookService.createBook(이케아_책장.getId(), 도서명_null_생성_요청))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("책 이름은 필수값입니다.");

        assertThatThrownBy(() -> bookService.createBook(이케아_책장.getId(), 도서명_공백_생성_요청))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("책 이름은 필수값입니다.");

        assertThatThrownBy(() -> bookService.createBook(이케아_책장.getId(), 도서명_공백_여러_개_생성_요청))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("책 이름은 필수값입니다.");
    }

    @Test
    void 책을_생성할_경우_저자는_필수값이다() {
        // given
        final Bookshelf 이케아_책장 = bookshelfRepository.save(new Bookshelf("이케아 5단 책장", 5));

        final CreateBookRequest 저자_null_생성_요청 = new CreateBookRequest(
                "객체지향의 사실과 오해",
                null,
                "9788998139766",
                LocalDate.of(2023, 6, 1),
                BookCategory.IT
        );

        final CreateBookRequest 저자_공백_생성_요청 = new CreateBookRequest(
                "객체지향의 사실과 오해",
                "",
                "9788998139766",
                LocalDate.of(2023, 6, 1),
                BookCategory.IT
        );

        final CreateBookRequest 저자_공백_여러_개_생성_요청 = new CreateBookRequest(
                "객체지향의 사실과 오해",
                "   ",
                "9788998139766",
                LocalDate.of(2023, 6, 1),
                BookCategory.IT
        );

        // when

        // then
        assertThatThrownBy(() -> bookService.createBook(이케아_책장.getId(), 저자_null_생성_요청))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("저자는 필수값입니다.");

        assertThatThrownBy(() -> bookService.createBook(이케아_책장.getId(), 저자_공백_생성_요청))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("저자는 필수값입니다.");

        assertThatThrownBy(() -> bookService.createBook(이케아_책장.getId(), 저자_공백_여러_개_생성_요청))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("저자는 필수값입니다.");
    }


}
