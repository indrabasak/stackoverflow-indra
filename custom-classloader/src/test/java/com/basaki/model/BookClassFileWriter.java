package com.basaki.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

/**
 * {@code BookClassFileWriter} is for reading {@code Book} class file and saving
 * it as a Base64 encoded string.
 * <p>
 *
 * @author Indra Basak
 * @since 12/6/17
 */
public class BookClassFileWriter {

    public static void main(String... args) throws IOException {
        String inputFile =
                "/Users/indra.basak/Development/examples/stackoverflow-indra/custom-classloader/target/test-classes/com/basaki/model/Bookx.class";

        InputStream inputStream = new FileInputStream(inputFile);
        long length = new File(inputFile).length();
        byte[] bytes = new byte[(int) length];
        inputStream.read(bytes);
        Base64.getEncoder().encode(bytes);
        //new sun.misc.BASE64Encoder().encode(bytes);
        String bookClazz = Base64.getUrlEncoder().encodeToString(bytes);
        //String bookClazz = new sun.misc.BASE64Encoder().encode(bytes);
        System.out.println(bookClazz);
    }
}
