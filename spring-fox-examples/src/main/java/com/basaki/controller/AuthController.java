package com.basaki.controller;

import com.basaki.model.AuthRequest;
import com.basaki.model.AuthResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * {@code AuthController} is an example controller.
 * <p/>
 *
 * @author Indra Basak
 * @since 11/15/17
 */
@RestController
@Slf4j
@Api(value = "Auth API",
        description = "Auth API",
        produces = "application/json", tags = {"API"})
public class AuthController {


    @ApiOperation(value = "Use to get token for internal applications")
    @PostMapping(value = "/tokens")
    public AuthResponse token(@Valid @RequestBody AuthRequest authRequest) {
        AuthResponse response = new AuthResponse();
        response.setName(authRequest.getUsername());

        return response;
    }
}
