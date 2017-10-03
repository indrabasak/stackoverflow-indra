package com.basaki.classloader;

import java.net.URL;
import java.net.URLClassLoader;

/**
 * {@code MyClassLoader} is a custom URL class loader which tries to load
 * classes (except 'java.' and 'javax.' classes) from the URLs before delegating
 * it to its parent.
 * <p/>
 *
 * @author Indra Basak
 * @since 10/3/17
 */
public class MyClassLoader extends URLClassLoader {

    /**
     * Constructs a custom class loader.
     *
     * @param urls   the URLs from which to load classes and resources
     * @param parent the parent class loader for delegation
     */
    public MyClassLoader(URL[] urls, ClassLoader parent) {
        super(urls, parent);
    }

    /**
     * Loads a class with the specified name.
     * <ol>
     * <li>Checks if a class has already been loaded.</li>
     * <li>If a class has not been loaded, delegates to the
     * parent class loaded if the class beloing to 'java.' or 'javax.'
     * packages.</li>
     * <ii>If the class is still null, tries to load the class from the
     * URLs.</ii>
     * <li>If the class is still null, tries to load it from the parent class
     * loader.</li>
     * <li>If the class is till not found, throws a class {@code
     * ClassNotFoundException}</li>
     * </ol>
     *
     * @param name    the name of the class to load
     * @param resolve indicates whether to resolve the loaded class or not.
     * @return the resulting object
     * @throws ClassNotFoundException if the class is not found
     */
    @Override
    protected synchronized Class<?> loadClass(String name,
            boolean resolve) throws ClassNotFoundException {

        // 1. Check if the class has already been loaded
        Class<?> clazz = findLoadedClass(name);

        ClassLoader parentCL = getParent();

        // 2. If the class is not loaded and the class name starts
        // with 'java.' or 'javax.', delegate loading to parent
        if (clazz == null && parentCL != null && (name.startsWith(
                "java.") || name.startsWith(
                "javax."))) {
            clazz = parentCL.loadClass(name);

        }

        // 3. If the class is still null, try to load the class from the URL
        // (since we have already taken care of 'java.' and 'javax.'
        if (clazz == null) {
            try {
                clazz = super.findClass(name);
            } catch (ClassNotFoundException e) {
                //don't do anything
            }
        }

        // 4. If the class is still null, let the parent class loader load it.
        // Previously, we allowed 'java.' and 'javax.' classes to be loaded
        // from parent
        if (clazz == null && parentCL != null) {
            clazz = parentCL.loadClass(name);
        }

        // 5. If the class is still null, throw a class not found exception
        if (clazz == null) {
            throw new ClassNotFoundException(name);
        }

        if (resolve) {
            resolveClass(clazz);
        }

        return clazz;
    }
}
