package com.basaki;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * {@code FilterByAttribute} filters a list by unique attributes.
 * <p>
 * Answer to stackoverflow question, https://stackoverflow.com/questions/46148694/java-8-filter-by-attribute/46149208#46149208
 *
 * @author Indra Basak
 * @since 9/11/17
 */
public class FilterByAttribute {
    public enum MyType {
        A, B, C, D;
    }

    public static class MyClass {
        private int val;

        private MyType type;

        public MyClass(int val, MyType type) {
            this.val = val;
            this.type = type;
        }

        public int getVal() {
            return val;
        }

        public MyType getType() {
            return type;
        }
    }

    public static void filterBySet(List<MyClass> list) {
        Set<MyType> typeSet = new HashSet<>();
        List<MyClass> result = list.stream()
                .filter(c -> typeSet.add(c.getType())).collect(
                        Collectors.toList());

        System.out.println("*** filterBySet() - START");
        result.forEach(
                c -> System.out.println(c.val + " " + c.type));
        System.out.println("*** filterBySet() - END");
    }

    public static void filterByGroupBy(List<MyClass> list) {
        List<MyClass>
                filtered = list.stream()
                .collect(Collectors.groupingBy(c -> c.type))
                .values()
                .stream()
                .map(l -> l.get(0))
                .collect(Collectors.toList());

        System.out.println("*** filterByGroupBy() - START");
        filtered.forEach(c -> System.out.println(c.val + " " + c.type));
        System.out.println("*** filterBySet() - END");
    }

    public static void main(String[] ags) {
        List<MyClass> list = Arrays.asList(new MyClass(1, MyType.A),
                new MyClass(2, MyType.A), new MyClass(4, MyType.B),
                new MyClass(5, MyType.B), new MyClass(3, MyType.C));

        filterBySet(list);
        filterByGroupBy(list);
    }
}
