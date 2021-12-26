package com.books.library.repos;

import com.books.library.dto.Book;

import java.util.List;
import java.util.Map;

public interface BookDao {

    // todo create
    void create(Book book);
    // todo readAll
    Map<String, List<String>> readAll();
    // todo readById
    Book readById(int id);
    // todo update
    void deleteById(int id);
    // todo delete
}
