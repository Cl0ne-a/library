package com.books.library.repos;

import com.books.library.dto.Genre;

import java.util.List;

public interface GenreDao {
    List<Genre> viewGenres();
    Genre getById(int id);
}
