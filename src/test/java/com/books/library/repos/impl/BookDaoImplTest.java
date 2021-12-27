package com.books.library.repos.impl;

import com.books.library.dto.Book;
import com.books.library.repos.maphelper.BookDataExtractor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.test.annotation.Commit;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.when;

@JdbcTest
@Import(BookDaoImpl.class)
class BookDaoImplTest {
    @Autowired
    private BookDaoImpl dao;
    @Mock
    BookDataExtractor extractor;
    @Mock
    ResultSet mockset;

    @Configuration
    static class NestedTestConfiguration {
        @Bean
        BookDataExtractor extractor() {
            return new BookDataExtractor();
        }
    }

    @Commit
    @DisplayName("Should create new book")
    @Test
    void create() throws SQLException {
        Book newTestBook = Book.builder()
                .title("What's up")
                .authorId(1)
                .genreId(2).build();
        Map<String, List<String>> expectedMappedData = Map.of(
                "not all bad is bad", List.of("comedy", "Sensei"),
                "Underground", List.of("horror", "Rockwell"),
                "Lets boogy voogy", List.of("Lets boogy voogy", "Rockwell"),
                "give me your hand", List.of("drama", "Mr.Blues"),
                "What's up", List.of("some genre", "some Author"));
        when(extractor.extractData(mockset)).thenReturn(expectedMappedData);

        dao.create(newTestBook);

        Assertions.assertEquals(
                5,
                dao.readAll().size());
    }

    @Test
    void readAll() {
    }

    @Test
    void readById() {
    }

    @Test
    void updateBook() {
    }

    @Test
    void deleteById() {
    }
}