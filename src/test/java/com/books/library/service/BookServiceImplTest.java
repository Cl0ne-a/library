package com.books.library.service;

import com.books.library.dto.Book;
import com.books.library.repos.BookDao;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

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
    void createNewBook() {
        Book expected = Book.builder().title("any title").genreId(1).authorId(1).build();
        ArgumentCaptor<Book> bookArgumentCaptor = ArgumentCaptor.forClass(Book.class);
//        todo this sounds strange here
        doNothing().when(bookDao).create(bookArgumentCaptor.capture());

        bookDao.create(expected);

//   todo how to test this service method? bookService.createNewBook(expected);
        var actualCreated = bookService.createNewBook(expected);

        assertTrue(actualCreated);
        assertEquals(bookArgumentCaptor.getValue(),expected);
    }

    @Test
    void getAll() {
        Map<String, List<String>> expected = Map.of(
                "not all bad is bad", List.of("comedy", "Sensei"),
                "Underground", List.of("horror", "Rockwell"),

                "no boogie", List.of("horror", "Rockwell"),
                "give me your hand", List.of("drama", "Mr.Blues"));

        when(bookDao.readAll()).thenReturn(expected);

        var actual = bookService.getAll();

        assertEquals(expected, actual);
    }

    @Test
    void getById() {
        Book expected = Book.builder().id(1).title("no boogie").genreId(1).authorId(1).build();
        var actual = bookService.getById(1);

        assertEquals(expected, actual);
    }

    @Test
    void updateBookName() {
        var actualUpdated = bookService.updateBookName(1, "no boogie");

        assertTrue(actualUpdated);
        assertEquals("no boogie", bookService.getById(1).getTitle());
    }

    @Test
    void deleteBookById() {
        var actual = bookService.deleteBookById(1);

        assertTrue(actual);
        assertThatThrownBy(() -> bookService.getById(1))
                .isInstanceOf(EmptyResultDataAccessException.class);
    }
}