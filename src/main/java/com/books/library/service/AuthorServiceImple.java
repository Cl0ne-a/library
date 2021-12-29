package com.books.library.service;

import com.books.library.dto.Author;
import com.books.library.repos.AuthorDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AuthorServiceImple implements AuthorService{
    private final AuthorDao authorDao;

    @Autowired
    public AuthorServiceImple(AuthorDao authorDao) {
        this.authorDao = authorDao;
    }

    @Override
    public List<Author> getAuthorsList() {
        return authorDao.viewAuthors();
    }
}
