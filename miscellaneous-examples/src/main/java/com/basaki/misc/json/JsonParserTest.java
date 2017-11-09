package com.basaki.misc.json;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.json.JSONArray;
import org.json.JSONObject;

public class JsonParserTest {

    public static void main(String... args) throws IOException {

        String content = new String(
                Files.readAllBytes(Paths.get("/Users/indra.basak/Development/examples/stackoverflow-indra/miscellaneous-examples/conf/customer_list.json")));

        JSONObject obj = new JSONObject(content);
        JSONArray listOfBranches = (JSONArray) obj.get("customers");
        for (int i = 0; i < listOfBranches.length(); i++) {
            System.out.println("Customer :" + listOfBranches.get(i));
        }
    }
}
