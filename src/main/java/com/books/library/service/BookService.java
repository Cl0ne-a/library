package com.books.library.service;

import com.books.library.dto.Book;
import com.books.library.repos.BookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BookService {

    private final BookDao bookDao;

    @Autowired
    public BookService(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    // todo create
//    public
    // todo readAll
    // todo readById
    // todo update
    // todo delete
}
