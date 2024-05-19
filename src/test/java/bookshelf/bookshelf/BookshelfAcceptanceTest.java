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
        final String 책장명 = 이케아_책장_생성_응답.get("name");
        final int 책장_층수 = 이케아_책장_생성_응답.get("floor");
        assertThat(책장명).isEqualTo("이케아 5단 책장");
        assertThat(책장_층수).isEqualTo(5);
    }

}
