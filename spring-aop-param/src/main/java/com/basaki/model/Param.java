package com.basaki.model;

/**
 * {@code Param} is an example geberic calss used with {@code ParamAspect}.
 * <p>
 *
 * @author Indra Basak
 * @since 10/12/17
 */
public class Param<T> {
    public T execute(T s) {
        return s;
    }
}
