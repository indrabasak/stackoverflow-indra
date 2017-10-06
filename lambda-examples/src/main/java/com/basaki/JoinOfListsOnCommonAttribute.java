package com.basaki;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.Setter;

/**
 * {@code JoinOfListsOnCommonAttribute} merges two lists based on a common
 * attribute and create a list of a different attribute.
 * <p>
 * Answer to stackoverflow question, https://stackoverflow.com/questions/46455845/using-java-lambda-to-join-two-list-on-a-common-attribute-and-collect-another-att/46456026#46456026
 *
 * @author Indra Basak
 * @since 9/27/17
 */
public class JoinOfListsOnCommonAttribute {
    @Getter
    @Setter
    public static class SampleClassOne {
        private String myProperty;
        private String myOtherProperty;
    }

    @Getter
    @Setter
    public static class SampleClassTwo {
        private String myProperty;
        private String someOtherProperty;
    }

    public static void main(String... args) {
        List<SampleClassOne> listOne = new ArrayList<>();
        SampleClassOne s1a = new SampleClassOne();
        s1a.setMyProperty("A");
        s1a.setMyOtherProperty("AA");
        listOne.add(s1a);

        SampleClassOne s1b = new SampleClassOne();
        s1b.setMyProperty("B");
        s1b.setMyOtherProperty("BB1");
        listOne.add(s1b);

        List<SampleClassTwo> listTwo = new ArrayList<>();
        SampleClassTwo s2c = new SampleClassTwo();
        s2c.setMyProperty("C");
        s2c.setSomeOtherProperty("CC");
        listTwo.add(s2c);

        SampleClassTwo s2b = new SampleClassTwo();
        s2b.setMyProperty("B");
        s2b.setSomeOtherProperty("BB2");
        listTwo.add(s2b);

        SampleClassTwo s3b = new SampleClassTwo();
        s3b.setMyProperty("A");
        s3b.setSomeOtherProperty("AA2");
        listTwo.add(s3b);

        List<String> someOtherPropertyList = listTwo.stream().filter(s2 ->
                listOne.stream().anyMatch(s1 -> s1.getMyProperty().equals(
                        s2.getMyProperty()))).map(
                SampleClassTwo::getSomeOtherProperty).collect(
                Collectors.toList());

        System.out.println("*************1");
        someOtherPropertyList.forEach(e -> System.out.println(e));
    }
}
