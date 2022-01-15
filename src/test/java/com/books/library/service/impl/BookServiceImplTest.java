package com.books.library.service.impl;

import com.books.library.dto.Book;
import com.books.library.repos.BookDao;
import com.books.library.service.BookService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@DisplayName("Book service: checks existence of instance before performing operation")
@SpringBootTest
class BookServiceImplTest {

    @Mock
    private BookDao bookDao;

    private final BookService bookService;

    @Autowired
    BookServiceImplTest(BookService bookService) {
        this.bookService = bookService;
    }

    @Test
    void updateBookName() {
        var actualUpdated = bookService.updateBookName(4, "not all bad is bad");

        assertTrue(actualUpdated);
    }

    @Test
    void deleteBookById() {
        var actual = bookService.deleteBookById(4);

        assertTrue(actual);
    }

    @Test
    void createNewBook() {
        Book expected = Book.builder().title("not all bad is bad").genreId(1).authorId(1).build();
        var actualCreated = bookService.createNewBook(expected);

        assertTrue(actualCreated);
    }

    @Test
    void getAll() {
        Map<String, List<String>> expected = Map.of(
                "not all bad is bad", List.of("comedy", "Sensei"),
                "Underground", List.of("horror", "Rockwell"),

                "Lets boogy voogy", List.of("horror", "Rockwell"),
                "give me your hand", List.of("drama", "Mr.Blues"));

        when(bookDao.readAll()).thenReturn(expected);

        var actual = bookService.getAll();

        assertThat(actual).containsExactlyInAnyOrderEntriesOf(expected);
    }

    @Test
    void getById() {
        Book expected = Book.builder().id(1).title("Lets boogy voogy").genreId(1).authorId(1).build();

        var actual = bookService.getById(1);

        assertEquals(expected, actual);
    }
}