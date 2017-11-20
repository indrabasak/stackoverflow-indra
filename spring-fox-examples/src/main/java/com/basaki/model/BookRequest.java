package com.basaki.model;

import lombok.Data;

/**
 * {@code BookRequest} represents a response during book creation.
 * <p/>
 *
 * @author Indra Basak
 * @since 11/20/17
 */
@Data
public class BookRequest {

    private String title;

    private String author;
}
