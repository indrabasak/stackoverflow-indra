package com.basaki.classloader;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * {@code MyClassLoaderTest} is the unit test calss for {@code MyClassLoader}.
 * <p/>
 *
 * @author Indra Basak
 * @since 10/3/17
 */
public class MyClassLoaderTest {

    public static final String JAR_PATH = "lib/commons-math3-3.6.1.jar";

    @Test
    public void testURLClassLoader() throws Exception {
        URL url = MyClassLoaderTest.class
                .getClassLoader().getResource(JAR_PATH);
        URL[] urls = new URL[]{url};

        URLClassLoader classLoader = new URLClassLoader(urls);

        // Load the target class
        Class<?> clazz = classLoader.loadClass(
                "org.apache.commons.math3.complex.Complex");
        if (clazz != null) {
            if (clazz.getProtectionDomain() != null) {
                String location =
                        clazz.getProtectionDomain().getCodeSource().getLocation().toString();
                String tokens[] = location.split("/");
                assertNotEquals("commons-math3-3.6.1.jar",
                        tokens[tokens.length - 1]);
            }
        }

        Class[] paramTypes = {double.class, double.class};
        Object[] paramValues = {2.0, 5.0};

        // Create a new instance from the loaded class
        Constructor<?> constructor = clazz.getConstructor(paramTypes);
        Object obj = constructor.newInstance(paramValues);
        // Getting a method from the loaded class and invoke it

        Method method = clazz.getMethod("reciprocal");
        method.invoke(obj);
    }

    @Test
    public void testMyClassLoader() throws Exception {
        URL url = MyClassLoaderTest.class
                .getClassLoader().getResource("lib/commons-math3-3.6.1.jar");
        URL[] urls = new URL[]{url};

        MyClassLoader classLoader =
                new MyClassLoader(urls, ClassLoader.getSystemClassLoader());

        // Load the target class
        Class<?> clazz = classLoader.loadClass(
                "org.apache.commons.math3.complex.Complex");
        if (clazz != null) {
            if (clazz.getProtectionDomain() != null) {
                String location =
                        clazz.getProtectionDomain().getCodeSource().getLocation().toString();
                String tokens[] = location.split("/");
                assertEquals("commons-math3-3.6.1.jar",
                        tokens[tokens.length - 1]);
            }
        }

        Class[] paramTypes = {double.class, double.class};
        Object[] paramValues = {2.0, 5.0};

        // Create a new instance from the loaded class
        Constructor<?> constructor = clazz.getConstructor(paramTypes);
        Object obj = constructor.newInstance(paramValues);
        // Getting a method from the loaded class and invoke it

        Method method = clazz.getMethod("reciprocal");
        method.invoke(obj);
    }
}
