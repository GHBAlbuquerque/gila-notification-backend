package study.notification.adapters.controllers;

import study.notification.adapters.dto.request.CreateNotificationDTO;
import study.notification.domain.enums.CategoryType;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class NotificationControllerTest {

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @Test
    void shouldSendNotificationSuccessfully() {
        final var dto = createNotificationDTO();

        given()
                .port(port)
                .contentType(ContentType.JSON)
                .body(dto)
                .when()
                .post("/notifications")
                .then()
                .statusCode(204);
    }

    @Test
    void shouldReturnAllNotificationsPaged() {
        given()
                .port(port)
                .contentType(ContentType.JSON)
                .when()
                .get("/notifications?size=10&page=0")
                .then()
                .statusCode(200)
                .body("page", equalTo(0));
    }

    private CreateNotificationDTO createNotificationDTO() {
        return new CreateNotificationDTO(CategoryType.SPORTS, "New match today!");
    }
}