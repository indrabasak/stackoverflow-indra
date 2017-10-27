package com.basaki.misc.clazzpath;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

/**
 * {@code FindResource} is an example of loading a resource file from
 * classpath.
 * <p/>
 * Answers to stackoverflow questions:
 * <ol>
 * <li>https://stackoverflow.com/questions/46848816/jar-manifest-class-path-use-folder-instead-of-jar</li>
 * </ol>
 *
 * @author Indra Basak
 * @since 10/27/17
 */
@SuppressWarnings({"squid:S106", "squid:S1481", "squid:S1854", "squid:S1148"})
public class FindResource {

    public FindResource(String fileName) {
        StringBuilder result = new StringBuilder("");

        URL properties =
                this.getClass().getClassLoader().getResource(fileName);

        File file = new File(properties.getFile());

        try (Scanner scanner = new Scanner(file)) {

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                result.append(line).append("\n");
            }

            scanner.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(result.toString());

    }

    public static void main(String... args) {
        FindResource findResource = new FindResource("foo.properties");

        URL properties = FindResource.class.getClassLoader().getResource(
                "foo.properties");
        System.out.println("** " + properties.toString());

    }
}
