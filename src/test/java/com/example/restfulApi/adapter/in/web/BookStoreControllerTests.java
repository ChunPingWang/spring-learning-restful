package com.example.restfulApi.adapter.in.web;

import com.example.restfulApi.Book;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class BookStoreControllerTests {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void shouldReturnABook() throws Exception {
        ResponseEntity<Book> response = this.restTemplate.getForEntity("http://localhost:" + port + "/bookstore/1111", Book.class);
        // 即將廢棄
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        // 新寫法
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}
