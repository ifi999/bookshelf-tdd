package bookshelf.bookshelf;

import bookshelf.booshelf.dto.CreateBookshelfRequest;
import bookshelf.booshelf.dto.CreateBookshelfResponse;
import bookshelf.booshelf.dto.GetBookshelfResponse;
import bookshelf.booshelf.dto.UpdateBookshelfRequest;
import bookshelf.booshelf.entity.Bookshelf;
import bookshelf.booshelf.repository.BookshelfRepository;
import bookshelf.booshelf.service.BookshelfService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Transactional
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
        final CreateBookshelfResponse 이케아_책장_생성_응답 = bookshelfService.createBookshelf(이케아_책장_생성_요청);
        final CreateBookshelfResponse 한샘_책장_생성_응답 = bookshelfService.createBookshelf(한샘_책장_생성_요청);

        // then
        assertThat(이케아_책장_생성_응답.getName()).isEqualTo(이케아_책장_생성_요청.getName());
        assertThat(이케아_책장_생성_응답.getFloor()).isEqualTo(이케아_책장_생성_요청.getFloor());
        assertThat(한샘_책장_생성_응답.getName()).isEqualTo(한샘_책장_생성_요청.getName());
        assertThat(한샘_책장_생성_응답.getFloor()).isEqualTo(한샘_책장_생성_요청.getFloor());
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
                "     ",
                1
        );

        // when

        // then
        assertThatThrownBy(() -> bookshelfService.createBookshelf(책장명_null_생성_요청))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("책장 이름은 필수값입니다.");

        assertThatThrownBy(() -> bookshelfService.createBookshelf(책장명_공백_생성_요청))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("책장 이름은 필수값입니다.");

        assertThatThrownBy(() -> bookshelfService.createBookshelf(책장명_공백_여러_개_생성_요청))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("책장 이름은 필수값입니다.");
    }

    @Test
    void 책장_생성할_경우_책장_층수는_0보다_커야한다() {
        // given
        final CreateBookshelfRequest 책장_층수_0_생성_요청 = new CreateBookshelfRequest(
                "이케아 책장",
                0
        );

        final CreateBookshelfRequest 책장_층수_음수_생성_요청 = new CreateBookshelfRequest(
                "이케아 책장",
                -1
        );

        // when

        // then
        assertThatThrownBy(() -> bookshelfService.createBookshelf(책장_층수_0_생성_요청))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("책장 층수는 0보다 커야합니다.");

        assertThatThrownBy(() -> bookshelfService.createBookshelf(책장_층수_음수_생성_요청))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("책장 층수는 0보다 커야합니다.");
    }

    @Test
    void 이미_등록된_책장명은_등록할_경우_예외가_발생한다() {
        // given
        bookshelfRepository.save(new Bookshelf("이케아 5단 책장", 5));

        final CreateBookshelfRequest 이케아_책장_생성_요청 = new CreateBookshelfRequest(
                "이케아 5단 책장",
                5
        );

        // when

        // then
        assertThatThrownBy(() -> bookshelfService.createBookshelf(이케아_책장_생성_요청))
                .isInstanceOf(EntityExistsException.class)
                .hasMessage("A bookshelf with the name '이케아 5단 책장' already exists.");
    }

    @Test
    void 책장을_조회한다() {
        // given
        final Bookshelf 이케아_5단_책장 = bookshelfRepository.save(new Bookshelf("이케아 5단 책장", 5));
        final Long 이케아_5단_책장_ID = 이케아_5단_책장.getId();
        final Bookshelf 한샘_4단_책장 = bookshelfRepository.save(new Bookshelf("한샘 4단 책장", 4));
        final Long 한샘_4단_책장_ID = 한샘_4단_책장.getId();

        // when
        final GetBookshelfResponse 이케아_책장_조회_응답 = bookshelfService.getBookshelf(이케아_5단_책장_ID);
        final GetBookshelfResponse 한샘_책장_조회_응답 = bookshelfService.getBookshelf(한샘_4단_책장_ID);

        // then
        assertThat(이케아_책장_조회_응답.getName()).isEqualTo(이케아_5단_책장.getName());
        assertThat(이케아_책장_조회_응답.getFloor()).isEqualTo(이케아_5단_책장.getFloor());
        assertThat(한샘_책장_조회_응답.getName()).isEqualTo(한샘_4단_책장.getName());
        assertThat(한샘_책장_조회_응답.getFloor()).isEqualTo(한샘_4단_책장.getFloor());
    }

    @Test
    void 존재하지_않는_책장ID로_조회할_경우_예외가_발생한다() {
        // given
        final long 존재하지_않는_책장ID = -1L;

        // when

        // then
        assertThatThrownBy(() -> bookshelfService.getBookshelf(존재하지_않는_책장ID))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessage("Failed to get bookshelf. Invalid bookshelf id: " + 존재하지_않는_책장ID);
    }

    @Test
    void 책장_정보를_수정한다() {
        // given
        final Bookshelf 이케아_5단_책장 = bookshelfRepository.save(new Bookshelf("이케아 5단 책장", 5));
        final Long 이케아_5단_책장_ID = 이케아_5단_책장.getId();
        final UpdateBookshelfRequest 이케아_책장_변경_요청 = new UpdateBookshelfRequest("이케아 4단 책장", 4);

        // when
        bookshelfService.updateBookshelf(이케아_책장_변경_요청, 이케아_5단_책장_ID);

        // then
        assertThat(이케아_5단_책장.getName()).isEqualTo("이케아 4단 책장");
        assertThat(이케아_5단_책장.getFloor()).isEqualTo(4);
    }

    @Test
    void 존재하지_않는_책장ID로_수정할_경우_예외가_발생한다() {
        // given
        final UpdateBookshelfRequest 이케아_책장_변경_요청 = new UpdateBookshelfRequest("이케아 4단 책장", 4);
        final long 존재하지_않는_책장ID = -1L;

        // when

        // then
        assertThatThrownBy(() -> bookshelfService.updateBookshelf(이케아_책장_변경_요청, 존재하지_않는_책장ID))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessage("Failed to update bookshelf. Invalid bookshelf id: " + 존재하지_않는_책장ID);
    }

    @Test
    void 이미_등록되어있는_책장명으로_수정할_경우_예외가_발생한다() {
        // given
        final CreateBookshelfRequest 한샘_책장_생성_요청 = new CreateBookshelfRequest("한샘 4단 책장", 4);
        final CreateBookshelfResponse 한샘_책장 = bookshelfService.createBookshelf(한샘_책장_생성_요청);
        final Long 한샘_책장_ID = 한샘_책장.getId();

        final CreateBookshelfRequest 이케아_책장_생성_요청 = new CreateBookshelfRequest("이케아 5단 책장", 5);
        bookshelfService.createBookshelf(이케아_책장_생성_요청);

        final UpdateBookshelfRequest 한샘_책장_변경_요청 = new UpdateBookshelfRequest("이케아 5단 책장", 5);

        // when

        // then
        assertThatThrownBy(() -> bookshelfService.updateBookshelf(한샘_책장_변경_요청, 한샘_책장_ID))
                .isInstanceOf(EntityExistsException.class)
                .hasMessage("A bookshelf with the name '이케아 5단 책장' already exists.");
    }

    @Test
    void 책장을_삭제한다() {
        // given
        final Bookshelf 이케아_5단_책장 = bookshelfRepository.save(new Bookshelf("이케아 5단 책장", 5));
        final Long 이케아_5단_책장_ID = 이케아_5단_책장.getId();

        // when
        bookshelfService.deleteBookshelf(이케아_5단_책장_ID);

        // then
        assertThatThrownBy(() -> bookshelfService.getBookshelf(이케아_5단_책장_ID))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessage("Failed to get bookshelf. Invalid bookshelf id: " + 이케아_5단_책장_ID);
    }

    @Test
    void 존재하지_않는_책장ID로_삭제할_경우_예외가_발생한다() {
        // given
        final long 존재하지_않는_책장ID = -1L;

        // when

        // then
        assertThatThrownBy(() -> bookshelfService.deleteBookshelf(존재하지_않는_책장ID))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessage("Failed to get bookshelf. Invalid bookshelf id: " + 존재하지_않는_책장ID);
    }

}
