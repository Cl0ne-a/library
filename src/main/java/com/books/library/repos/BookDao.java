package com.books.library.repos;

import com.books.library.dto.Book;

import java.util.List;
import java.util.Map;

public interface BookDao {

    boolean exists(Book book);

    void create(Book book);

    Map<String, List<String>> readAll();

    Book readById(int id);

    void updateBook(int id, String title);

    void deleteById(int id);
}
