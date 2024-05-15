package bookshelf;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BookshelfServiceTest {

    @Test
    void 책장을_생성한다() {
        // given
        final CreateBookshelfRequest 책장_생성_요청 = new CreateBookshelfRequest(
                "이케아 5단 책장",
                5
        );

        // when
        final BookshelfService bookshelfService = new BookshelfService();
        final CreateBookshelfResponse 책장_생성_응답 = bookshelfService.addBookshelf(책장_생성_요청);

        // then
        assertThat(책장_생성_응답.getName()).isEqualTo(책장_생성_요청.getName());
        assertThat(책장_생성_응답.getFloor()).isEqualTo(책장_생성_요청.getFloor());
    }

}
