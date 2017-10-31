package com.basaki.controller;

import com.basaki.model.Book;
import com.basaki.service.BookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * {@code BookController} is the spring REST controller for book API.
 * <p/>
 *
 * @author Indra Basak
 * @since 10/4/17
 */
@RestController
@Slf4j
@Api(value = "Book API",
        description = "Book API",
        produces = "application/json", tags = {"API"})
public class BookController {

    @Setter
    @Value("${spring.datasource.url}")
    private String url;

    @Autowired
    private BookService service;

    @ApiOperation(
            value = "Creates a book.",
            response = Book.class)
    @RequestMapping(method = RequestMethod.POST, value = "/books")
    public Book create(@RequestBody Book book) {
        System.out.println("*** " + book);

        return book;
    }

    @ApiOperation(
            value = "Retrieves a book.",
            notes = "Requires book identifier",
            response = Book.class)
    @RequestMapping(method = RequestMethod.GET, produces = {
            MediaType.APPLICATION_JSON_VALUE}, value = "/books/{id}")
    public Book read(@PathVariable("id") Integer id) {

        return service.read(id);
    }

    @ApiOperation(
            value = "Ping",
            response = String.class)
    @RequestMapping(method = RequestMethod.GET, value = "/ping")
    public String ping() {

        return "pong";
    }
}
