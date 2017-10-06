package com.basaki;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * {@code MergeOfListContainingList} merges a list of objects where each object
 * contains ttwo separate list of different objects.
 * <p>
 * Answer to stackoverflow question, https://stackoverflow.com/questions/46373808/merging-a-list-of-objects-containing-lists/46374757#46374757
 *
 * @author Indra Basak
 * @since 9/23/17
 */
public class MergeOfListContainingList {
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Foo {
        List<Cat> cats = new ArrayList<>();
        List<Dog> dogs = new ArrayList<>();
    }

    @Data
    @AllArgsConstructor
    public static class Cat {
        int id;
    }

    @Data
    @AllArgsConstructor
    public static class Dog {
        String name;
    }

    public static void flatMapMerge(ArrayList<Foo> fooList) {
        List<Cat> catList = fooList.stream().
                map(d -> d.getCats()).
                flatMap(l -> l.stream()).
                collect(Collectors.toList());

        List<Dog> dogList = fooList.stream().
                map(d -> d.getDogs()).
                flatMap(l -> l.stream()).
                collect(Collectors.toList());

        Foo foo = new Foo(catList, dogList);

        System.out.println("*** flatMapMerge() - START");
        foo.cats.forEach(c -> System.out.println(c));
        foo.dogs.forEach(d -> System.out.println(d));
        System.out.println("*** flatMapMerge() - END");
    }

    public static void reduceMerge(ArrayList<Foo> fooList) {
        Foo foo = fooList.stream().reduce(new Foo(), (f1, f2) -> {
            f1.cats.addAll(f2.cats);
            f1.dogs.addAll(f2.dogs);
            return f1;
        });

        System.out.println("*** reduceMerge() - START");
        foo.cats.forEach(c -> System.out.println(c));
        foo.dogs.forEach(d -> System.out.println(d));
        System.out.println("*** reduceMerge() - END");
    }

    public static void main(String[] args) {
        ArrayList<Foo> fooList = new ArrayList<>();

        List<Cat> cats1 = new ArrayList<>();
        cats1.add(new Cat(1));
        cats1.add(new Cat(2));

        List<Dog> dogs1 = new ArrayList<>();
        dogs1.add(new Dog("dog 1"));
        dogs1.add(new Dog("dog 2"));

        fooList.add(new Foo(cats1, dogs1));

        List<Cat> cats2 = new ArrayList<>();
        cats2.add(new Cat(3));
        cats2.add(new Cat(4));

        List<Dog> dogs2 = new ArrayList<>();
        dogs2.add(new Dog("dog 3"));
        dogs2.add(new Dog("dog 4"));

        fooList.add(new Foo(cats2, dogs2));

        flatMapMerge(fooList);
        reduceMerge(fooList);
    }
}
