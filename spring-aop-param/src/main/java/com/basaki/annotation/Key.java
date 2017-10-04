package com.basaki.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * {@code Key} is annotation for marking a method parameter.
 * <p/>
 * Given a method like this:
 * <pre><code>
 *     public String someMethod({@literal @}Key String name) {
 *         return "Hello " + name;
 *     }
 * </code></pre>
 * <p>
 *
 * @author Indra Basak
 * @since 10/4/17
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface Key {
}