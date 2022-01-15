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

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("BookDao performs to: ")
@JdbcTest
@Import(BookDaoImpl.class)
class BookDaoImplTest {
    private final BookDaoImpl dao;

    @Autowired
    BookDaoImplTest(BookDaoImpl dao) {
        this.dao = dao;
    }

    @Configuration
    static class NestedTestConfiguration {
        @Bean
        BookDataExtractor dataExtractor() {
            return new BookDataExtractor();
        }
    }

    @DisplayName("checks correctly if book exists")
    @Test
    void exists() {
        var expectedRetrievedFromDataBase = dao.readById(1).getId();
        boolean actual = dao.exists(expectedRetrievedFromDataBase);

        Assertions.assertTrue(actual);
    }

    @DisplayName("read by Id")
    @Test
    void readById() {
        Book expected = Book.builder()
                .id(1)
                .title("Lets boogy voogy")
                .genreId(1)
                .authorId(1).build();

        Book actual = dao.readById(1);

        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @DisplayName("create new book in DB")
    @Test
    void create() {
        Book expected = Book.builder()
                .id(5)
                .title("What's up")
                .genreId(1)
                .authorId(2).build();

        dao.create(expected);

        Book actual = dao.readById(5);
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
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

    @DisplayName("update book title")
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