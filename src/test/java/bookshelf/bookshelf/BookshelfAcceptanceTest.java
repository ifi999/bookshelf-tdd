package bookshelf.bookshelf;

import bookshelf.booshelf.dto.CreateBookshelfRequest;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class BookshelfAcceptanceTest {

    @Test
    void 책장을_생성한다() {
        // given
        final CreateBookshelfRequest 이케아_책장_생성_요청 = new CreateBookshelfRequest(
                "이케아 5단 책장",
                5
        );

        // when
        final JsonPath 이케아_책장_생성_응답 =
                given()
                    .log().all()
                    .body(이케아_책장_생성_요청)
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                    .post("/bookshelf")
                .then()
                    .statusCode(HttpStatus.CREATED.value())
                    .log().all()
                .extract()
                    .jsonPath();

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
        final JsonPath 이케아_책장_생성_응답 =
                given()
                    .log().all()
                    .body(이케아_책장_생성_요청)
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                    .post("/bookshelf")
                .then()
                    .statusCode(HttpStatus.CREATED.value())
                    .log().all()
                .extract()
                    .jsonPath();

        final Long 이케아_책장_ID = 이케아_책장_생성_응답.getLong("id");

        // when
        final JsonPath 이케아_책장_조회_응답 =
                given()
                    .log().all()
                .when()
                    .get("/bookshelf/{id}", 이케아_책장_ID)
                .then()
                    .statusCode(HttpStatus.OK.value())
                    .log().all()
                .extract()
                    .jsonPath();

        // then
        final Long 조회_책장_ID = 이케아_책장_조회_응답.getLong("id");
        final String 조회_책장명 = 이케아_책장_조회_응답.getString("name");
        final int 조회_책장_층수 = 이케아_책장_조회_응답.getInt("floor");
        assertThat(조회_책장_ID).isEqualTo(이케아_책장_ID);
        assertThat(조회_책장명).isEqualTo("이케아 5단 책장");
        assertThat(조회_책장_층수).isEqualTo(5);
    }

}
