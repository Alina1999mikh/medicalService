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
class NoteControllerTest {

    //        auth.inMemoryAuthentication()
//                .withUser("user1").password(passwordEncoder().encode("user1Pass"))
//                .authorities("ROLE_USER");
//        TestRestTemplate testRestTemplate
//                = new TestRestTemplate("user", "passwd");
//        ResponseEntity<String> response = testRestTemplate.
//                getForEntity("/v1/note", String.class);
//        LoginPage loginPage = new LoginPage(driver);
//        loginPage.successLogin("root","root");
    //        TestRestTemplate testRestTemplate
//                = new TestRestTemplate("user", "passwd");
    @Autowired
    TestRestTemplate template;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Test
    @Operation(summary = "create", security = @SecurityRequirement(name="basicAuth"))
    @DisplayName("Should create a note")
    void createNote() {
        TestRestTemplate testRestTemplate
                = new TestRestTemplate("user", "passwd");
        ResponseEntity<String> response = testRestTemplate.
                getForEntity("/v1/note", String.class);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertThat(
                template.exchange("/v1/note", HttpMethod.POST, new HttpEntity<>("""
                        {
                            "uuid": "b5871b6b-e0e4-4053-9fc8-2782a217ce0a",
                            "lab": "invitro",
                            "test": "Fe",
                            "date": "2021-03-02",
                            "result": "6.42",
                            "referenceRange": "9-30.4",
                            "unit": "мкмоль/л",
                            "comment": "Тестовый тест"
                        }
                        """, headers()), String.class)
        )
                // then
                .extracting(ResponseEntity::getStatusCode)
                .isEqualTo(HttpStatus.CREATED);
    }

    @Operation(summary = "create", security = @SecurityRequirement(name="basicAuth"))
    @Test
    @DisplayName("Should return note by UUID")
    void findNoteByUuid() {
        // given
        assertThat(template.exchange("/v1/note", HttpMethod.POST, new HttpEntity<>("""
                {
                            "uuid": "b5871b6b-e0e4-4053-9fc8-2782a217ce0a",
                            "lab": "invitro",
                            "test": "Fe",
                            "date": "2021-03-02",
                            "result": "6.42",
                            "referenceRange": "9-30.4",
                            "unit": "мкмоль/л",
                            "comment": "Тестовый тест"
                }
                """, headers()), String.class)
        )
                .extracting(ResponseEntity::getStatusCode)
                .isEqualTo(HttpStatus.CREATED);

        assertThat(template.exchange("/v1/note", HttpMethod.POST, new HttpEntity<>("""
                {
                            "uuid": "ed0bdada-dfad-42e3-aebd-f6e637dbd2a8",
                            "lab": "invitro",
                            "test": "HbA1C",
                            "date": "2021-03-02",
                            "result": "5.5",
                            "referenceRange": "0-6",
                            "unit": "%",
                            "comment": "Тестовый тест 2"
                }
                """, headers()), String.class)
        )
                .extracting(ResponseEntity::getStatusCode)
                .isEqualTo(HttpStatus.CREATED);

        // when
        assertThat(
                template.getForEntity("/v1/note/{uuid}", String.class, "b5871b6b-e0e4-4053-9fc8-2782a217ce0a")
        )
                // then
                .extracting(ResponseEntity::getBody)
                .isEqualTo("""
                        {"uuid":"b5871b6b-e0e4-4053-9fc8-2782a217ce0a","lab":"invitro","test":"Fe","date":"2021-03-02","result":"6.42","referenceRange":"9-30.4","unit":"мкмоль/л","comment":"Тестовый тест"}""");
    }

    @Test
    @DisplayName("Should return NOT_FOUND  if note doesn't exist")
    @Operation(summary = "create", security = @SecurityRequirement(name="basicAuth"))
    void shouldReturnNotFoundIfNoteNotExists() {
        template.exchange("/v1/note", HttpMethod.POST, new HttpEntity<>("""
                {
                    "uuid": "b5871b6b-e0e4-4053-9fc8-2782a217ce0a",
                            "lab": "invitro",
                            "test": "Fe",
                            "date": "02.03.2021",
                            "result": "6.42",
                            "referenceRange": "9-30.4",
                            "unit": "мкмоль/л"
                }
                """, headers()), String.class);
        // when
        assertThat(
                template.getForEntity("/v1/note/{uuid}", String.class, "a8871b6b-e0e4-4053-9fc8-2782a217ce0a")
        )
                // then
                .extracting(ResponseEntity::getStatusCode)
                .isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    @Operation(summary = "create", security = @SecurityRequirement(name="basicAuth"))
    @DisplayName("Should delete a note")
    void deleteNoteByUuid() {
        // given
        template.exchange("/v1/note", HttpMethod.POST, new HttpEntity<>("""
                {
                    "uuid": "b5871b6b-e0e4-4053-9fc8-2782a217ce0a",
                            "lab": "invitro",
                            "test": "Fe",
                            "date": "02.03.2021",
                            "result": "6.42",
                            "referenceRange": "9-30.4",
                            "unit": "мкмоль/л"
                }
                """, headers()), String.class);
        // when
        assertThat(
                template.exchange(
                        "/v1/note/{uuid}",
                        HttpMethod.DELETE,
                        new HttpEntity<>(new HttpHeaders()),
                        String.class,
                        "b5871b6b-e0e4-4053-9fc8-2782a217ce0a"
                )
        )
                // then
                .extracting(ResponseEntity::getStatusCode)
                .isEqualTo(HttpStatus.OK);

        assertThat(
                template.getForEntity("/v1/note/{uuid}", String.class, "b5871b6b-e0e4-4053-9fc8-2782a217ce0a")
        )
                .extracting(ResponseEntity::getStatusCode)
                .isEqualTo(HttpStatus.NOT_FOUND);
    }
//Authorization: Basic dXNlcjE6dXNlcjFQYXNz
    @AfterEach
    void afterEach() {
        jdbcTemplate.execute("DELETE FROM NOTES");
    }

    private HttpHeaders headers() {
        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Basic dXNlcjE6dXNlcjFQYXNz");
        return headers;
    }
}