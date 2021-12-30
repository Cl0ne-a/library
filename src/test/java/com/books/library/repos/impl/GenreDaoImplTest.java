package com.books.library.repos.impl;

import com.books.library.dto.Genre;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("GenreDao performs to: ")
@JdbcTest
@Import(GenreDaoImpl.class)
class GenreDaoImplTest {

    private final GenreDaoImpl genreDao;

    @Autowired
    GenreDaoImplTest(GenreDaoImpl genreDao) {
        this.genreDao = genreDao;
    }

    @DisplayName("display all genre list")
    @Test
    void viewAuthors() {
        List<Genre> expected =
                List.of(Genre.builder().id(1).genre("horror").build(),
                        Genre.builder().id(2).genre("drama").build(),
                        Genre.builder().id(3).genre("comedy").build());
        var actual = genreDao.viewGenres();

        assertThat(actual).containsExactlyInAnyOrderElementsOf(expected);
    }

    @DisplayName("get genre by id")
    @Test
    void getById() {
        Genre expected = Genre.builder().id(1).genre("horror").build();
        var actual = genreDao.getById(1);
        assertThat(actual).isEqualTo(expected);
    }
}