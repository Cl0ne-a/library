package com.books.library.ui;

import com.books.library.dto.Author;
import com.books.library.dto.Book;
import com.books.library.dto.Genre;
import com.books.library.service.AuthorService;
import com.books.library.service.BookService;
import com.books.library.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@ShellComponent
public class ShellUi {
    private final BookService bookService;
    private final AuthorService authorService;
    private final GenreService genreService;

    @Autowired
    public ShellUi(BookService bookService,
                   AuthorService authorService,
                   GenreService genreService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.genreService = genreService;
    }

    @ShellMethod(key="authors", value = "display list of available authors")
    public List<Author> getAvailableAuthors() {
        return authorService.getAuthorsList();
    }

    @ShellMethod(key="genres", value = "display list of available genres")
    public List<Genre> getAvailableGenres() {
        return genreService.getGenresList();
    }

    @ShellMethod(key = "create", value = "Create new book." +
            "To perform this operation enter the book title, " +
            "also choose genre and author from existing list and set them")
    public boolean createNewBook(String title, int genreId, int authorId) {
        // todo chenge to display what was created
        var genre = genreService.byId(genreId).getGenre();
        String author = "authorService.getAuthorByID(authorId";

        return bookService
                .createNewBook(
                        Book.builder()
                                .title(title)
                                .genreId(genreId)
                                .authorId(authorId).build());
    }

    @ShellMethod(key = "booklist", value = "get all books in the library")
    public Map<String, List<String>> getAllBooks() {
        return bookService.getAll();
    };

    @ShellMethod(key = "byid", value = "getting book by its  id")
    String getById(int id) {
        var book = bookService.getById(id);
        var genre = genreService.byId(book.getGenreId()).getGenre();
        return String.format("Book exists. \nTitle %s \ngenre %s", book.getTitle(), genre);
    }

    @ShellMethod(key = "update", value = "you can update book here, if its id is already in database")
    String updateBookName(int id, String title) {
        var updated = bookService.updateBookName(id, title);
        return String.format("Book updated for id%d set title %s", id, title);
    };


    @ShellMethod(key = "delete", value = "delete book from database")
    boolean deleteBookById(int id) {
        return bookService.deleteBookById(id);
    };
}
