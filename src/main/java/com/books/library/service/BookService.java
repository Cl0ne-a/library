package com.books.library.service;

import com.books.library.dto.Book;

import java.util.List;
import java.util.Map;

public interface BookService {
    boolean createNewBook(Book book);

    Map<String, List<String>> getAll();

    Book getById(int id);

    boolean updateBookName(int id, String title);

    boolean deleteBookById(int id);
}
