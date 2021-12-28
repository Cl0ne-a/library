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

    public boolean createNewBook(Book book) {
        boolean created = false;
        int id = book.getId();
        String title = book.getTitle();
        int authorId = book.getAuthorId();
        int genreId = book.getGenreId();
        Book ifExists = bookDao.readById(id);
        if (    ifExists!= null
                && !ifExists.getTitle().equals(title)
                && ifExists.getAuthorId() != authorId
                && ifExists.getGenreId() != genreId) {
            bookDao.create(book);
            created = true;
        }
        return created;
    }

    public Map<String, List<String>> getAll() {
        return bookDao.readAll();
    }

    public Book getById(int id) {
        Book book = null;
        if(bookDao.readById(id) == null) {
            // todo handle exception
            System.out.println("no such book available");
        } else {
            book = bookDao.readById(id);
        }
        return book;
    }

    public boolean updateBookName(int id, String title) {
        boolean updated = false;

        Book ifExists = bookDao.readById(id);

        if (ifExists != null) {
            bookDao.updateBook(id, title);
            updated = true;
        }
        return updated;
    }

    public boolean deleteBookById(int id) {
        boolean deleted = false;

        Book ifExists = bookDao.readById(id);

        if (ifExists != null) {
            bookDao.deleteById(id);
            deleted = true;
        }
        return deleted;
    }
}
