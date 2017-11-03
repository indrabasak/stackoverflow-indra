package com.basaki.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * {@code MyAnno} is annotation for marking a method.
 * <p/>
 * Given a method like this:
 * <pre><code>
 *     {@literal @}MyAnno(cl = MyClass.class, description = "desc")
 *     public String someMethod(String name) {
 *         return "Hello " + name;
 *     }
 * </code></pre>
 * <p>
 *
 * @author Indra Basak
 * @since 11/3/17
 */
@Inherited
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnno {
    Class cl();

    String description() default "";
}
