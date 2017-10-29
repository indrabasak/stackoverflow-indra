package com.basaki.classloader;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

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

    /**
     * The contextClassLoader mechanisms is not used by the basic Java
     * operations like new. It's only there so various frameworks can access the
     * context class loader in charge and load resources, classes, etc. Java
     * will always use the classloader that loaded the code that is executing.
     * It's the one that you access via ChangeLoaderTest.class.getClassLoader()
     * -- and there is nothing you can do about this one.
     * https://stackoverflow.com/questions/10192453/java-classloader-change
     * @throws Exception
     */
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

    @Test
    public void testMyClassLoaderFail() throws Exception {
        URL url = MyClassLoaderTest.class
                .getClassLoader().getResource("lib/commons-math3-3.6.1.jar");
        URL[] urls = new URL[]{url};

        MyClassLoader classLoader =
                new MyClassLoader(urls, ClassLoader.getSystemClassLoader());
        // Set context loader to custom class loader
        Thread.currentThread().setContextClassLoader(classLoader);

        // Fetch system class loader
        Field scl = ClassLoader.class.getDeclaredField("scl");
        // Set accessible
        scl.setAccessible(true);
        scl.set(null, classLoader);

        System.out.println(Thread.currentThread().getId());
        org.apache.commons.math3.complex.Complex complex =
                new org.apache.commons.math3.complex.Complex(2.0, 5.0);
        org.apache.commons.math3.complex.Complex reciprocal =
                complex.reciprocal();

        Class<?> clazz = complex.getClass();

        if (clazz != null) {
            if (clazz.getProtectionDomain() != null) {
                String location =
                        clazz.getProtectionDomain().getCodeSource().getLocation().toString();
                System.out.println("location: " + location);
                String tokens[] = location.split("/");
                assertNotEquals("commons-math3-3.6.1.jar",
                        tokens[tokens.length - 1]);
            }
        }

        assertNotNull(reciprocal);
        System.out.println(reciprocal);

        System.out.println(reciprocal);
    }
}
