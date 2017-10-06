package com.basaki.service;

import org.springframework.stereotype.Service;

/**
 * {@code TenantService} is a service which prints out the name of the user
 * in the basic authentication taken.
 * <p/>
 *
 * @author Indra Basak
 * @since 10/5/17
 */
@Service
public class TenantService {

    public void sayHello(String user) {
        System.out.println("Hello " + user + "!");
    }
}
