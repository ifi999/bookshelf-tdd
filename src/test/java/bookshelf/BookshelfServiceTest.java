package bookshelf;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BookshelfServiceTest {

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
        final BookshelfService bookshelfService = new BookshelfService();
        final CreateBookshelfResponse 이케아_책장_생성_응답 = bookshelfService.addBookshelf(이케아_책장_생성_요청);
        final CreateBookshelfResponse 한샘_책장_생성_응답 = bookshelfService.addBookshelf(한샘_책장_생성_요청);

        // then
        assertThat(이케아_책장_생성_응답.getName()).isEqualTo(이케아_책장_생성_요청.getName());
        assertThat(이케아_책장_생성_응답.getFloor()).isEqualTo(이케아_책장_생성_요청.getFloor());
        assertThat(한샘_책장_생성_응답.getName()).isEqualTo(한샘_책장_생성_요청.getName());
        assertThat(한샘_책장_생성_응답.getFloor()).isEqualTo(한샘_책장_생성_요청.getFloor());
    }

}
