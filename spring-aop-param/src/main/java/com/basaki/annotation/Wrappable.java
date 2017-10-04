package com.basaki.annotation;


import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * {@code Wrappable} is annotation for marking a method.
 * <p/>
 * Given a method like this:
 * <pre><code>
 *     {@literal @}Wrappable(name = "fancyName")
 *     public String someMethod(String name) {
 *         return "Hello " + name;
 *     }
 * </code></pre>
 * <p>
 *
 * @author Indra Basak
 * @since 10/4/17
 */
@Inherited
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Wrappable {
    String name() default "";
}
