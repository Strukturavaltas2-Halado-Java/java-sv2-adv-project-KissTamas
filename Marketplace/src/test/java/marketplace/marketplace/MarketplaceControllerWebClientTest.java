package marketplace.marketplace;


import marketplace.marketplace.dto.*;
import marketplace.marketplace.model.Category;
import marketplace.marketplace.service.MarketplaceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.zalando.problem.Problem;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(statements = {"delete from ads", "delete from users"})
public class MarketplaceControllerWebClientTest {

    @Autowired
    WebTestClient webTestClient;

    @Autowired
    MarketplaceService marketplaceService;
    UserDto userDtoTest;
    AdDto adDtoTest;

    @BeforeEach
    void init() {
        userDtoTest = webTestClient.post()
                .uri("api/users")
                .bodyValue(new CreateUserCommand("Béla", "negycsillag", "bela@gmail.com"))
                .exchange()
                .expectBody(UserDto.class)
                .returnResult().getResponseBody();

        webTestClient.post()
                .uri("/api/users")
                .bodyValue(new CreateUserCommand("Józsi", "ezegyjelszo", "jozsi@gmail.com"))
                .exchange();

        adDtoTest = webTestClient.post()
                .uri(uriBuilder -> uriBuilder.path("/api/users/{id}/ads").build(userDtoTest.getId()))
                .bodyValue(new CreateAdCommand(Category.VEHICLE, 5000, "Budapest", "fekete roller"))
                .exchange()
                .expectBody(AdDto.class)
                .returnResult().getResponseBody();
    }

    @Test
    void testCreateUser() {
        assertThat(userDtoTest.getName()).isEqualTo("Béla");
        assertThat(userDtoTest.getEmail()).isEqualTo("bela@gmail.com");
    }

    @Test
    void testCreateAd() {
        assertThat(adDtoTest.getPlace()).isEqualTo("Budapest");
        assertThat(adDtoTest.getPrice()).isEqualTo(5000);
    }

    @Test
    void testGetUserById() {
        webTestClient.get()
                .uri("api/users/{id}", userDtoTest.getId())
                .exchange()
                .expectBody(UserDto.class)
                .value(userDto -> assertThat(userDto.getName()).isEqualTo("Béla"))
                .value(userDto -> assertThat(userDto.getEmail()).isEqualTo("bela@gmail.com"));
    }

    @Test
    void testGetAdById() {
        webTestClient.get()
                .uri("api/users/ads/{id}", adDtoTest.getId())
                .exchange()
                .expectBody(AdDto.class)
                .value(adDto -> assertThat(adDto.getPrice()).isEqualTo(5000))
                .value(adDto -> assertThat(adDto.getPlace()).isEqualTo("Budapest"));
    }

    @Test
    void testGetAdByMinprice() {
        webTestClient.post()
                .uri(uriBuilder -> uriBuilder.path("/api/users/{id}/ads").build(userDtoTest.getId()))
                .bodyValue(new CreateAdCommand(Category.VEHICLE, 999_999, "Szentes", "Volvo"))
                .exchange()
                .expectBody(AdDto.class);

        webTestClient.get()
                .uri(builder -> builder.path("/api/users/ads").queryParam("minprice", 900_000).build())
                .exchange()
                .expectBodyList(AdDto.class)
                .value(list -> assertThat(list)
                        .hasSize(1)
                        .extracting(AdDto::getPlace)
                        .containsOnly("Szentes"));
    }

    @Test
    void testUpdateAd() {
        webTestClient.put()
                .uri("/api/users/ads/{id}", adDtoTest.getId())
                .bodyValue(new UpdateAdCommand(Category.VEHICLE, 5000, "Vecsés", "fekete roller"))
                .exchange()
                .expectBody(AdDto.class)
                .value(adDto -> assertEquals("Vecsés", adDto.getPlace()));
    }

    @Test
    void testUpdateUser() {
        webTestClient.put()
                .uri("/api/users/{id}", userDtoTest.getId())
                .bodyValue(new UpdateUserCommand("Béla", "uj_jelszo", "bela@gmail.com"))
                .exchange()
                .expectBody(UserDto.class)
                .value(userDto -> assertEquals("uj_jelszo", userDto.getPassword()));
    }

    @Test
    void testUserNotFound() {
        Problem p = webTestClient.post()
                .uri("api/users/50000/ads")
                .bodyValue(new CreateAdCommand(Category.VEHICLE, 5000, "Budapest", "fekete roller"))
                .exchange()
                .expectBody(Problem.class).returnResult().getResponseBody();
        assertThat(p).isNotNull();
        assertEquals("User not found with id 50000", p.getDetail());
    }

    @Test
    void testAdNotFound() {
        Problem p = webTestClient.get()
                .uri("api/users/ads/50000")
                .exchange()
                .expectBody(Problem.class).returnResult().getResponseBody();
        assertThat(p).isNotNull();
        assertEquals("Ad not found with id 50000", p.getDetail());
    }

    @Test
    void testDeleteUser() {
        webTestClient.delete()
                .uri("/api/users/{id}", userDtoTest.getId())
                .exchange();
        webTestClient.get()
                .uri("/api/users")
                .exchange()
                .expectBodyList(UserDto.class)
                .value(list -> assertThat(list)
                        .hasSize(1));
    }

    @Test
    void testDeleteAd() {
        webTestClient.delete()
                .uri("/api/users/ads/{id}", adDtoTest.getId())
                .exchange();
        webTestClient.get()
                .uri("/api/users/ads")
                .exchange()
                .expectBodyList(AdDto.class)
                .value(list -> assertThat(list)
                        .hasSize(0));
    }

    @Test
    void createUserWithWrongEmailAddress() {
        Problem p = webTestClient.post()
                .uri("api/users")
                .bodyValue(new CreateUserCommand("Józsi", "ezegyjelszo", "@gmail.com"))
                .exchange()
                .expectBody(Problem.class).returnResult().getResponseBody();
        assertThat(p).isNotNull();
        assertEquals("Invalid email address: @gmail.com", p.getDetail());
    }
}
