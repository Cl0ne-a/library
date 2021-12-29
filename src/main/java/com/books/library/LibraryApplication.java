package com.books.library;

import com.books.library.dto.Book;
import com.books.library.repos.BookDao;
import com.books.library.service.BookService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LibraryApplication {

    public static void main(String[] args) {
        var ctx = SpringApplication.run(LibraryApplication.class, args);
        var x = ctx.getBean(BookService.class);
        System.out.println(x.createNewBook(Book.builder().id(5).title("ff").genreId(3).authorId(2).build()));
    }
}
