package com.books.library.repos;

import com.books.library.dto.Author;

import java.util.List;

public interface AuthorDao {
    List<Author> viewAuthors();
    Author getById(int id);
}
