package com.books.library;

import com.books.library.service.BookService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LibraryApplication {

    public static void main(String[] args) {
        var ctx = SpringApplication.run(LibraryApplication.class, args);
        var x = ctx.getBean(BookService.class);
    }
}
