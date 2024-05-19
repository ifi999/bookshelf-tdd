package bookshelf.bookshelf;

import bookshelf.booshelf.dto.CreateBookshelfRequest;
import bookshelf.booshelf.dto.CreateBookshelfResponse;
import bookshelf.booshelf.entity.Bookshelf;
import bookshelf.booshelf.repository.BookshelfRepository;
import bookshelf.booshelf.service.BookshelfService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
public class BookshelfServiceMockTest {

    @Mock
    private BookshelfRepository bookshelfRepository;

    @InjectMocks
    private BookshelfService bookshelfService;

    @Test
    void 책장을_생성한다() {
        // given
        final CreateBookshelfRequest 이케아_책장_생성_요청 = new CreateBookshelfRequest("이케아 5단 책장", 5);
        final Bookshelf 이케아_책장 = new Bookshelf("이케아 5단 책장", 5);
        when(bookshelfRepository.save(any(Bookshelf.class))).thenReturn(이케아_책장);

        // when
        final CreateBookshelfResponse 이케아_책장_생성_응답 = bookshelfService.addBookshelf(이케아_책장_생성_요청);

        // then
        assertThat(이케아_책장_생성_응답.getName()).isEqualTo(이케아_책장_생성_요청.getName());
        assertThat(이케아_책장_생성_응답.getFloor()).isEqualTo(이케아_책장_생성_요청.getFloor());

        verify(bookshelfRepository, times(1)).save(any(Bookshelf.class));
    }

    @Test
    void 책장_생성할_경우_책장명은_필수값이다() {
        // given
        final CreateBookshelfRequest 책장명_null_생성_요청 = new CreateBookshelfRequest(
                null,
                1
        );

        final CreateBookshelfRequest 책장명_공백_생성_요청 = new CreateBookshelfRequest(
                "",
                1
        );

        final CreateBookshelfRequest 책장명_공백_여러_개_생성_요청 = new CreateBookshelfRequest(
                "   ",
                1
        );

        // when

        // then
        assertThatThrownBy(() -> bookshelfService.addBookshelf(책장명_null_생성_요청))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("책장 이름은 필수값입니다.");

        assertThatThrownBy(() -> bookshelfService.addBookshelf(책장명_공백_생성_요청))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("책장 이름은 필수값입니다.");

        assertThatThrownBy(() -> bookshelfService.addBookshelf(책장명_공백_여러_개_생성_요청))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("책장 이름은 필수값입니다.");
    }

}
