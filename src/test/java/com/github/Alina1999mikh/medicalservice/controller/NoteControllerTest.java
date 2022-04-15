package com.github.Alina1999mikh.medicalservice.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
class NoteControllerTest {

    @Autowired
    TestRestTemplate template;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Test
    @DisplayName("Should create a note")
    void createNote() {
        // when
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

    @AfterEach
    void afterEach() {
        jdbcTemplate.execute("DELETE FROM NOTES");
    }

    private HttpHeaders headers() {
        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }
}