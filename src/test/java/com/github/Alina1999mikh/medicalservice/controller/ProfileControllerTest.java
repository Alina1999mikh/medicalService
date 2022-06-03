package com.github.Alina1999mikh.medicalservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
class ProfileControllerTest {

    @Autowired
    TestRestTemplate template= new TestRestTemplate("user1", "user1Pass");

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Test
    @DisplayName("Should create a profile")
    void createProfile() {
        assertThat(
                template.exchange("/v1/profile", HttpMethod.POST, new HttpEntity<>("""
                        {
                          "username": "deswier",
                          "fName": "Elena",
                          "sName": "Mikhaleva",
                          "date": "1999-06-02",
                          "gender": "F"
                        }
                        """, headers()), String.class)
        )
                .extracting(ResponseEntity::getStatusCode)
                .isEqualTo(HttpStatus.CREATED);
    }

    @Operation(summary = "create", security = @SecurityRequirement(name="basicAuth"))
    @Test
    @DisplayName("Should return profile by username")
    void findProfileByUsername() {
        // given
        assertThat(template.exchange("/v1/profile", HttpMethod.POST, new HttpEntity<>("""
                {
                          "username": "deswier",
                          "fname": "Elena",
                          "sname": "Mikhaleva",
                          "date": "1999-06-02",
                          "gender": "F"
                }
                """, headers()), String.class)
        )
                .extracting(ResponseEntity::getStatusCode)
                .isEqualTo(HttpStatus.CREATED);

        assertThat(template.exchange("/v1/profile", HttpMethod.POST, new HttpEntity<>("""
                {
                          "username": "zer0chance",
                          "date": "2000-02-20",
                          "gender": "M",
                          "fname": "Evgeny",
                          "sname": "Ignatenko"
                }
                }
                """, headers()), String.class)
        )
                .extracting(ResponseEntity::getStatusCode)
                .isEqualTo(HttpStatus.CREATED);

        // when
        assertThat(
                template.exchange("/v1/profile/{username}", HttpMethod.GET, new HttpEntity<>(headers()), String.class,"b5871b6b-e0e4-4053-9fc8-2782a217ce0a"))
                .extracting(ResponseEntity::getBody)
                .isEqualTo("""
        {"user_id":1,"uuid":"b5871b6b-e0e4-4053-9fc8-2782a217ce0a","lab":"invitro","test":"Fe","date":"2021-03-02","result":"6.42","referenceRange":"9-30.4","unit":"мкмоль/л","comment":"Тестовый тест"}""");
    }

    @Test
    @DisplayName("Should return NOT_FOUND  if profile doesn't exist")
    @Operation(summary = "create", security = @SecurityRequirement(name="basicAuth"))
    void shouldReturnNotFoundIfProfileNotExists() {
        template.exchange("/v1/profile", HttpMethod.POST, new HttpEntity<>("""
                {
                          "username": "deswier",
                          "date": "1999-06-02",
                          "gender": "F",
                          "fname": "Alina",
                          "sname": "Mikhaleva"
                }
                """, headers()), String.class);

        assertThat(
                template.exchange("/v1/profile/{username}", HttpMethod.GET, new HttpEntity<>(headers()), String.class,"a8871b6b-e0e4-4053-9fc8-2782a217ce0a"))
                .extracting(ResponseEntity::getStatusCode)
                .isEqualTo(HttpStatus.NOT_FOUND);
    }

    @AfterEach
    void afterEach() {
        jdbcTemplate.execute("DELETE FROM PROFILES");
    }

    private HttpHeaders headers() {
        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Basic dXNlcjE6dXNlcjFQYXNz");
        return headers;
    }
}