package com.basaki;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * {@code ChangeMapKeys} changes keys of a list of maps.
 * <p>
 * Answer to stackoverflow question, https://stackoverflow.com/questions/46967382/java-stream-change-key-of-maps-in-list/46967817#46967817
 *
 * @author Indra Basak
 * @since 10/26/17
 */
@SuppressWarnings({"squid:S106", "squid:S3599", "squid:S1192", "squid:S1171"})
public class ChangeMapKeys {

    public static void translate1(List<Map<String, String>> list) {
        List<Map<String, String>> list2 = list.stream().map(e -> {
            Map<String, String> map = new HashMap<>();
            for (Map.Entry x : e.entrySet()) {
                if (x.getKey().equals("id")) {
                    map.put("sample_id", (String) x.getValue());
                } else if (x.getKey().equals("display")) {
                    map.put("sample", (String) x.getValue());
                } else {
                    map.put((String) x.getKey(), (String) x.getValue());
                }
            }

            return map;
        }).collect(Collectors.toList());

        System.out.println(list2);
    }

    public static void translate2(List<Map<String, String>> list) {

        List<Map<String, String>> list3 = list.stream().map(
                e -> e.entrySet().stream().collect(Collectors.toMap(
                        x -> {
                            if (x.getKey().equals("id")) {
                                return "sample_id";
                            } else if (x.getKey().equals("display")) {
                                return "sample";
                            } else {
                                return x.getKey();
                            }
                        },
                        Map.Entry::getValue))).collect(Collectors.toList());

        System.out.println(list3);
    }

    public static void main(String... args) {
        List<Map<String, String>> list = new ArrayList<Map<String, String>>() {{
            add(new HashMap<String, String>() {{
                put("id", "1");
                put("display", "foo");
            }});
            add(new HashMap<String, String>() {{
                put("id", "2");
                put("display", "bar");
            }});
        }};

        System.out.println(list.toString());

        translate1(list);
        translate2(list);
    }
}
