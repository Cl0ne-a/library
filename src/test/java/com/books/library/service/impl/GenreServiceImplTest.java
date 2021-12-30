package com.books.library.service.impl;

import com.books.library.dto.Genre;
import com.books.library.repos.GenreDao;
import com.books.library.service.GenreService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class GenreServiceImplTest {
    @Mock
    private GenreDao authorDao;

    private final GenreService genreService;

    @Autowired
    GenreServiceImplTest(GenreService genreService) {
        this.genreService = genreService;
    }

    @DisplayName("display genre list")
    @Test
    void getGenresList() {
        List<Genre> expected = List.of(
                Genre.builder().id(1).genre("horror").build(),
                Genre.builder().id(2).genre("drama").build(),
                Genre.builder().id(3).genre("comedy").build()
        );
        var actual = genreService.getGenresList();
        assertThat(actual).containsExactlyInAnyOrderElementsOf(expected);
    }

    @DisplayName("get genre by id")
    @Test
    void byId() {
        Genre expected = Genre.builder().id(1).genre("horror").build();
        var actual = genreService.byId(1);

        assertThat(actual).isEqualTo(expected);
    }
}