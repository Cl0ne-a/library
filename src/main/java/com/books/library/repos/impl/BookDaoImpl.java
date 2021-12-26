package com.books.library.repos.impl;

import com.books.library.dto.Book;
import com.books.library.repos.BookDao;
import com.books.library.repos.maphelper.BookDataExtractor;
import org.h2.result.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

@Repository
public class BookDaoImpl implements BookDao {
    private final NamedParameterJdbcOperations jdbc;
    @Autowired
    private BookDataExtractor books;

    public BookDaoImpl(NamedParameterJdbcOperations jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public void create(Book book) {
        int genreId = book.getGenreId();
        int authorId = book.getAuthorId();
        String title = book.getTitle();
        jdbc.update(
                "insert into BOOK (TITLE, GENRE_ID, AUTHOR_ID) values (:title, :authorId, :genreId)",
                Map.of("title", title, "genreId", genreId, "authorId", authorId));
    }

    @Override
    public Map<String, List<String>> readAll() {

        String sql = "select title, g.genre, a.name from PUBLIC.BOOK join GENRE g on BOOK.GENRE_ID = g.ID join AUTHOR A on BOOK.AUTHOR_ID = A.ID;";
        return jdbc.query(sql, books);
    }

    @Override
    public Book readById(int id) {
        Map<String, Object> map = Collections.singletonMap("id", id);
        String sql = "select id, title, genre_id, author_id from book where id = :id";
        return jdbc.queryForObject(sql,  map, new BookMapper());
    }

    @Override
    public void deleteById(int id) {
        Map<String, Object> map = Collections.singletonMap("id", id);
        String sql = "delete from book where id = :id";
        jdbc.update(sql, map);
    }

    public static class BookMapper implements RowMapper<Book> {


        @Override
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
            int id = rs.getInt("id");
            String title = rs.getString("title");
            int genreId = rs.getInt("genre_id");
            int authorId = rs.getInt("author_id");
            return Book.builder().id(id).title(title).genreId(genreId).authorId(authorId).build();
        }
    }
}
