package com.basaki.misc.lombok;

import lombok.Builder;
import lombok.Data;

/**
 * {@code MyModel} is an example of Lombok skelton @{code lombok.Builder}. It
 * changes the value of field before it's set.
 * <p>
 * Answers to stackoverflow questions:
 * <ol>
 * <li>https://stackoverflow.com/questions/47185622/additional-methods-in-java-builder-class-lombok-annotation/47194753#47194753</li>
 * </ol>
 *
 * @author Indra Basak
 * @since 11/8/17
 */
@Data
@Builder
public class MyModel {

    private String myField1;

    private String myField2;

    private String myField3;

    public static class MyModelBuilder {
        public MyModelBuilder myField3(String myField3) {
            this.myField3 = "Hello " + myField3;
            return this;
        }
    }

    public static void main(String... args) {
        MyModel model = MyModel.builder()
                .myField1("value for field 1")
                .myField2("value for field 2")
                .myField3("value for field 3")
                .build();

        System.out.println(model);
    }
}
