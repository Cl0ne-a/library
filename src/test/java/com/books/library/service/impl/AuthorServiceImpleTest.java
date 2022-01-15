package com.books.library.service.impl;

import com.books.library.dto.Author;
import com.books.library.repos.AuthorDao;
import com.books.library.service.AuthorService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
class AuthorServiceImpleTest {

    @Mock
    private AuthorDao authorDao;

    private final AuthorService authorService;

    @Autowired
    AuthorServiceImpleTest(AuthorService authorService) {
        this.authorService = authorService;
    }

    @DisplayName("display author list")
    @Test
    void getAuthorsList() {
        var expected = List.of(
                Author.builder().id(1).name("Rockwell").build(),
                Author.builder().id(2).name("Mr.Blues").build(),
                Author.builder().id(3).name("Sensei").build());
        when(authorDao.viewAuthors()).thenReturn(expected);
        var actual = authorService.getAuthorsList();

        assertThat(actual).containsExactlyInAnyOrderElementsOf(expected);
    }

    @DisplayName("get author by id")
    @Test
    void byId() {
        Author expected = Author.builder().id(1).name("Rockwell").build();
        var actual = authorService.byId(1);

        assertThat(actual).isEqualTo(expected);
    }
}