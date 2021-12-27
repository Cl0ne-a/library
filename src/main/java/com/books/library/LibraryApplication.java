package com.books.library;

import com.books.library.dto.Book;
import com.books.library.repos.BookDao;
import org.h2.tools.Console;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;

@SpringBootApplication
public class LibraryApplication {

    public static void main(String[] args) throws SQLException {
        var ctx = SpringApplication.run(LibraryApplication.class, args);
        BookDao dao = ctx.getBean(BookDao.class);
        Book b = Book.builder().title("Cherry lady").authorId(2).genreId(2).build();

        dao.create(b);
        dao.readAll().forEach((title, dataSet) -> System.out.printf("Title: %s, genre: %s, author: %s\n", title, dataSet.get(0), dataSet.get(1)));
        System.out.println(dao.readById(2));
        dao.deleteById(2);
        dao.readAll().forEach((title, dataSet) -> System.out.printf("Title: %s, genre: %s, author: %s\n", title, dataSet.get(0), dataSet.get(1)));
        dao.updateBook(3, "Get goat");
        // todo create handler for EmptyResultDataAccessException
        // System.out.println(dao.readById(2));

        Console.main(args);
    }
}
