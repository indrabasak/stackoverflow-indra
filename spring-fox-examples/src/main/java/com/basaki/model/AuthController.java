package com.basaki.model;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * {@code AuthController} is the second example auth controller. Showing how
 * to avoid name conflict.
 * <p/>
 *
 * @author Indra Basak
 * @since 11/15/17
 */
@RestController("test-auth-controller")
@Slf4j
@Api(description = "test-auth-controller",
        produces = "application/json", tags = {"2"})
public class AuthController {

    @ApiOperation(value = "ping")
    @GetMapping(value = "/ping")
    public String ping() {
        return "pong";
    }
}
