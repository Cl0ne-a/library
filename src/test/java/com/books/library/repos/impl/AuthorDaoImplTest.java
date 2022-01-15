package com.books.library.repos.impl;

import com.books.library.dto.Author;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("AuthorDao performs to: ")
@JdbcTest
@Import(AuthorDaoImpl.class)
class AuthorDaoImplTest {

    private final AuthorDaoImpl dao;

    @Autowired
    AuthorDaoImplTest(AuthorDaoImpl dao) {
        this.dao = dao;
    }

    @DisplayName("get author by id")
    @Test
    void getById() {
        Author expected = Author.builder().id(1).name("Rockwell").build();
        var actual = dao.getById(1);

        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("display all authors")
    @Test
    void viewAuthors() {
        List<Author> expected = List.of(
                Author.builder().id(1).name("Rockwell").build(),
                Author.builder().id(2).name("Mr.Blues").build(),
                Author.builder().id(3).name("Sensei").build());
        var actual = dao.viewAuthors();

        assertThat(actual).containsExactlyInAnyOrderElementsOf(expected);
    }
}