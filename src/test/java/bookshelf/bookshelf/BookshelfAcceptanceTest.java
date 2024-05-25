package bookshelf.bookshelf;

import bookshelf.booshelf.dto.CreateBookshelfRequest;
import bookshelf.booshelf.dto.UpdateBookshelfRequest;
import bookshelf.util.DatabaseCleaner;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class BookshelfAcceptanceTest {

    private static final String BOOKSHELF_API_PATH = "/bookshelf";

    @Autowired
    private DatabaseCleaner databaseCleaner;

    @BeforeEach
    void setUp() {
        databaseCleaner.execute();
    }

    @Test
    void 책장을_생성한다() {
        // given
        final CreateBookshelfRequest 이케아_책장_생성_요청 = new CreateBookshelfRequest(
                "이케아 5단 책장",
                5
        );

        // when
        final JsonPath 이케아_책장_생성_응답 = callPostApi(이케아_책장_생성_요청, BOOKSHELF_API_PATH).jsonPath();

        // then
        final String 책장명 = 이케아_책장_생성_응답.getString("name");
        final int 책장_층수 = 이케아_책장_생성_응답.getInt("floor");
        assertThat(책장명).isEqualTo("이케아 5단 책장");
        assertThat(책장_층수).isEqualTo(5);
    }

    @Test
    void 책장을_조회한다() {
        // given
        final CreateBookshelfRequest 이케아_책장_생성_요청 = new CreateBookshelfRequest(
                "이케아 5단 책장",
                5
        );
        final JsonPath 이케아_책장_생성_응답 = callPostApi(이케아_책장_생성_요청, BOOKSHELF_API_PATH).jsonPath();
        final Long 이케아_책장_ID = 이케아_책장_생성_응답.getLong("id");

        // when
        final JsonPath 이케아_책장_조회_응답 = callGetApi(이케아_책장_ID).jsonPath();

        // then
        final Long 조회_책장_ID = 이케아_책장_조회_응답.getLong("id");
        final String 조회_책장명 = 이케아_책장_조회_응답.getString("name");
        final int 조회_책장_층수 = 이케아_책장_조회_응답.getInt("floor");
        assertThat(조회_책장_ID).isEqualTo(이케아_책장_ID);
        assertThat(조회_책장명).isEqualTo("이케아 5단 책장");
        assertThat(조회_책장_층수).isEqualTo(5);
    }

    @Test
    void 책장을_수정한다() {
        // given
        final CreateBookshelfRequest 한샘_책장_생성_요청 = new CreateBookshelfRequest("한샘 4단 책장", 4);
        final JsonPath 한샘_책장_생성_응답 = callPostApi(한샘_책장_생성_요청, BOOKSHELF_API_PATH).jsonPath();
        final long 한샘_책장_ID = 한샘_책장_생성_응답.getLong("id");

        final UpdateBookshelfRequest 한샘_책장_수정_요청 = new UpdateBookshelfRequest("한샘 5단 책장", 5);

        // when
        given()
            .body(한샘_책장_수정_요청)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .log().all()
        .when()
            .put("/bookshelf/{id}", 한샘_책장_ID)
        .then()
            .statusCode(HttpStatus.OK.value())
            .log().all();

        // then
        final JsonPath 한샘_책장_조회_응답 = callGetApi(한샘_책장_ID).jsonPath();

        final String 수정_책장명 = 한샘_책장_조회_응답.getString("name");
        final int 수정_책장_층수 = 한샘_책장_조회_응답.getInt("floor");
        assertThat(수정_책장명).isEqualTo("한샘 5단 책장");
        assertThat(수정_책장_층수).isEqualTo(5);
    }

    private ExtractableResponse<Response> callPostApi(final Object requestBody, final String path) {
        return given()
                    .log().all()
                    .body(requestBody)
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                    .post(path)
                .then()
                    .statusCode(HttpStatus.CREATED.value())
                    .log().all()
                .extract();
    }

    private ExtractableResponse<Response> callGetApi(final Long id) {
        return given()
                    .log().all()
                .when()
                    .get(BOOKSHELF_API_PATH + "/{id}", id)
                .then()
                    .statusCode(HttpStatus.OK.value())
                    .log().all()
                .extract();
    }

}
