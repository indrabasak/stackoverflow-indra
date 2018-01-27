package com.basaki.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.boot.actuate.autoconfigure.ManagementContextConfiguration;
import org.springframework.boot.actuate.endpoint.mvc.AbstractMvcEndpoint;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * {@code TestController} is an example Spring actuator endpoint.
 * <p/>
 *
 * @author Indra Basak
 * @since 1/27/18
 */
@Configuration
@ManagementContextConfiguration
public class TestController extends AbstractMvcEndpoint {
    public TestController() {
        super("/test", false, true);
    }

    @GetMapping(value = "/get", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public OkResponse getInfo() {
        return new OkResponse(200, "ok");
    }

    @JsonPropertyOrder({"status", "message"})
    public static class OkResponse {
        @JsonProperty
        private Integer status;

        @JsonProperty
        private String message;

        public OkResponse(Integer status, String message) {
            this.status = status;
            this.message = message;
        }

        public Integer getStatus() {
            return status;
        }

        public String getMessage() {
            return message;
        }
    }
}
