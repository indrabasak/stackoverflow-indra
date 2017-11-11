Spring AspectJ Weaving Examples 
=================================

# AspectJ Source Weaving
However, you can intercept private methods by taking advantage of AspectJ source weaving. In source weaving, the weaver becomes part of the compiler. The weaver acts as a compiler by processing the source code and generating woven Java bytecode. Instead of `javac` compiler, it uses `ajc` compiler.

Maven provides [aspectj-maven-plugin](http://www.mojohaus.org/aspectj-maven-plugin/) to work with AspectJ source weaving.

-`MyAspect.java` has the solution related to stackoverflow question on [Aspect does not capture method from Scheduled](https://stackoverflow.com/questions/47072554/aspect-does-not-capture-method-from-scheduled/47104087#47104087)

- `SpringFrameworkClassAspect.java` has the solution related to stackoverflow question on [AspectJ before spring boot starts](https://stackoverflow.com/questions/47208270/aspectj-before-spring-boot-starts)