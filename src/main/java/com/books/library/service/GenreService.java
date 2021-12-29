package com.books.library.service;

import com.books.library.dto.Genre;

import java.util.List;

public interface GenreService {
    List<Genre> getGenresList();
    Genre byId(int id);
}
