package com.basaki.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * {@code UserController} is the spring REST controller for retrieving users
 * <p/>
 *
 * @author Indra Basak
 * @since 11/20/17
 */
@RestController
@Slf4j
@Api(value = "User API",
        description = "User API",
        produces = "application/json", tags = {"2"})
public class UserController {

    private String JSON_USERS = "[\n" +
            "  {\n" +
            "    \"creator\" : 5,\n" +
            "    \"contributors\" : [\n" +
            "      {\n" +
            "        \"id\" : 3\n" +
            "      },\n" +
            "      {\n" +
            "        \"id\" : 6\n" +
            "      }\n" +
            "    ]\n" +
            "  },\n" +
            "  {\n" +
            "    \"creator\" : 9,\n" +
            "    \"contributors\" : [\n" +
            "      {\n" +
            "        \"id\" : 3\n" +
            "      },\n" +
            "      {\n" +
            "        \"id\" : 5\n" +
            "      }\n" +
            "    ]\n" +
            "  }\n" +
            "]";

    @ApiOperation(
            value = "Retrieves all users.",
            response = String.class)
    @RequestMapping(method = RequestMethod.GET, produces = {
            MediaType.APPLICATION_JSON_VALUE}, value = "/users")
    public String read() {
        return JSON_USERS;
    }
}
