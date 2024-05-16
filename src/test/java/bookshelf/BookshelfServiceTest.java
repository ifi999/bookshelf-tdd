package bookshelf;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class BookshelfServiceTest {

    @Autowired
    private BookshelfService bookshelfService;

    @Autowired
    private BookshelfRepository bookshelfRepository;

    @Test
    void 책장을_생성한다() {
        // given
        final CreateBookshelfRequest 이케아_책장_생성_요청 = new CreateBookshelfRequest(
                "이케아 5단 책장",
                5
        );

        final CreateBookshelfRequest 한샘_책장_생성_요청 = new CreateBookshelfRequest(
                "한샘 4단 책장",
                4
        );

        // when
        final CreateBookshelfResponse 이케아_책장_생성_응답 = bookshelfService.addBookshelf(이케아_책장_생성_요청);
        final CreateBookshelfResponse 한샘_책장_생성_응답 = bookshelfService.addBookshelf(한샘_책장_생성_요청);

        // then
        assertThat(이케아_책장_생성_응답.getName()).isEqualTo(이케아_책장_생성_요청.getName());
        assertThat(이케아_책장_생성_응답.getFloor()).isEqualTo(이케아_책장_생성_요청.getFloor());
        assertThat(한샘_책장_생성_응답.getName()).isEqualTo(한샘_책장_생성_요청.getName());
        assertThat(한샘_책장_생성_응답.getFloor()).isEqualTo(한샘_책장_생성_요청.getFloor());
    }

    @Test
    void 책장을_조회한다() {
        // given
        final Bookshelf 이케아_5단_책장 = bookshelfRepository.save(new Bookshelf("이케아 5단 책장", 5));
        final Long 이케아_5단_책장_ID = 이케아_5단_책장.getId();

        // when
        final GetBookshelfResponse 책장_조회_응답 = bookshelfService.getBookshelf(이케아_5단_책장_ID);

        // then
        assertThat(책장_조회_응답.getName()).isEqualTo(이케아_5단_책장.getName());
        assertThat(책장_조회_응답.getFloor()).isEqualTo(이케아_5단_책장.getFloor());
    }

}
