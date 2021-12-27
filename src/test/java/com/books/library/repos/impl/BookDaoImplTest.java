package com.books.library.repos.impl;

import com.books.library.dto.Book;
import com.books.library.repos.maphelper.BookDataExtractor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("BookDao performs to: ")
@JdbcTest
@Import(BookDaoImpl.class)
class BookDaoImplTest {
    @Autowired
    private BookDaoImpl dao;

    @Configuration
    static class NestedTestConfiguration {
        @Bean
        BookDataExtractor bookDataExtractor() {
            return new BookDataExtractor();
        }
    }

    // todo: здесь падает на запуске всех тестов
    //  с EmptyResultDataAccessException:
    //  Incorrect result size: expected 1, actual 0,
    //  и отрабатывает при одиночном запуске
    @Sql("/test.sql")
    @DisplayName("read by Id")
    @Test
    void readById() {
        Book newTestBook = Book.builder()
                .id(5)
                .title("whats up")
                .genreId(1)
                .authorId(2).build();

        Book expected = dao.readById(5);

        assertThat(expected).usingRecursiveComparison().isEqualTo(newTestBook);
    }

    @DisplayName("create new book in DB")
    @Test
    void create() {
        Book newTestBook = Book.builder()
                .id(5)
                .title("What's up")
                .genreId(1)
                .authorId(2).build();
        // before create method
        boolean containsNewTestBook = dao.readAll().containsKey("What's up");
        Assertions.assertFalse(containsNewTestBook);

        dao.create(newTestBook);

        // after create method
        boolean doesContainNewTestBook = dao.readAll().containsKey("What's up");
        Assertions.assertTrue(doesContainNewTestBook);
    }

    @DisplayName("return existing list from bookDao")
    @Test
    void readAll() {
        Map<String, List<String>> expectedMappedData = Map.of(
                "not all bad is bad", List.of("comedy", "Sensei"),
                "Underground", List.of("horror", "Rockwell"),
                "give me your hand", List.of("drama", "Mr.Blues"),
                "Lets boogy voogy", List.of("horror", "Rockwell"));
        var actual = dao.readAll();

        assertThat(actual).containsExactlyInAnyOrderEntriesOf(expectedMappedData);
    }

    @Test
    void updateBook() {
        int bookUnderTestId = 1;
        dao.updateBook(bookUnderTestId, "Book of infinity");

        Assertions.assertEquals(
                "Book of infinity",
                dao.readById(bookUnderTestId).getTitle());
    }

    @DisplayName("delete book by id")
    @Test
    void deleteById() {
        Book bookToDelete = dao.readById(3);

        System.out.println(bookToDelete);
        Assertions.assertNotNull(bookToDelete);

        dao.deleteById(3);
        assertThatThrownBy(() -> dao.readById(3))
                .isInstanceOf(EmptyResultDataAccessException.class);
    }
}