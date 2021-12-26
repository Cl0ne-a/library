package com.books.library.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Book {
    private int id;
    private String title;
    private int genreId;
    private int authorId;
}
