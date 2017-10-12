package com.basaki.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * {@code Book} represents a book entity.
 * <p/>
 *
 * @author Indra Basak
 * @since 10/4/17
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    private Integer id;

    private String title;

    private String author;
}
