package com.example.restfulApi;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
public class BookListJsonTests {
    @Autowired
    private JacksonTester<Book> json;

    @Autowired
    private JacksonTester<Book[]> jsonList;
    private Book[] books;

    @BeforeEach
    void setUp() {
        books = Arrays.array(
                new Book("Spring in Action", 32.45),
                new Book("Microservices Design Pattern", 31.98),
                new Book("Cloud Native Java", 45.23)
        );
    }

    @Test
    void bookSerializeTest() throws IOException {
        Book book = new Book("Spring in Action", 32.45);
        assertThat(json.write(book)).isStrictlyEqualToJson("expected.json");
        assertThat(json.write(book)).hasJsonPathStringValue("@.title");
        assertThat(json.write(book)).extractingJsonPathStringValue("@.title")
                .isEqualTo("Spring in Action");
        assertThat(json.write(book)).hasJsonPathNumberValue("@.price");
        assertThat(json.write(book)).extractingJsonPathNumberValue("@.price")
                .isEqualTo(32.45);

    }
    @Test
    void bookDeserializationTest() throws IOException {
        String expected = """
                { "title": "Spring in Action",
                "price": 32.45}
            """;
        assertThat(json.parse(expected)).isEqualTo(new Book("Spring in Action", 32.45));
        assertThat(json.parseObject(expected).title()).isEqualTo("Spring in Action");
        assertThat(json.parseObject(expected).price()).isEqualTo(32.45);
    }

    @Test
    void bookListSerializationTest() throws IOException {
        assertThat(jsonList.write(books)).isStrictlyEqualToJson("list.json");
    }

    @Test
    void bookListDeserializationTest() throws IOException {
        String expected = """
           [     
                { "title": "Spring in Action",
                  "price": 32.45},
                {
                  "title": "Microservices Design Pattern",
                  "price": 31.98
                },
                {
                    "title": "Cloud Native Java",
                     "price": 45.23
                }
           ]     
            """;

        assertThat(jsonList.parse(expected)).isEqualTo(books);

    }
}
