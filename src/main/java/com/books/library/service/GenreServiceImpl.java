package com.books.library.service;

import com.books.library.dto.Genre;
import com.books.library.repos.GenreDao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService{
    private final GenreDao genreDao;

    public GenreServiceImpl(GenreDao genreDao) {
        this.genreDao = genreDao;
    }

    @Override
    public List<Genre> getGenresList() {
        return genreDao.viewGenres();
    }

    @Override
    public Genre byId(int id) {
        return genreDao.getById(id);
    }
}
