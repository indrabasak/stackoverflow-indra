Swagger Code Generation Example
=================================
This is an example related to stackoverflow question on [How to develop a simple REST Client using Swagger codegen?
](https://stackoverflow.com/questions/57545884/how-to-develop-a-simple-rest-client-using-swagger-codegen)

This example has only one REST controller, `EmployeeController`. The Swagger group is called `employee`. 
The application starts at port `8080`.

# Swagger Endpoints
Once you have successfully deployed the war file, the Swagger endpoints can be accessed at:

1. Testing Swagger 2.0 JSON API documentation

   `http://localhost:8080/v2/api-docs?group=employee`

2. Testing Swagger UI

    `http://localhost:8080/swagger-ui.html`

# Download Swagger Codegen Executable
You can download the [swagger-codegen-cli-2.4.7.jar](https://repo1.maven.org/maven2/io/swagger/swagger-codegen-cli/2.4.7/swagger-codegen-cli-2.4.7.jar) 
from the Maven Central Repository.

# Generating Client Code
Now that you have the Swagger Codegen JAR, you can generate the REST client by executing the following command:

```
java -jar swagger-codegen-cli-2.4.7.jar generate \
  -i http://localhost:8080/v2/api-docs?group=employee \
  -l java 
  -o swagger-codegen-client
```

## Options
Though Swagger Codegen CLI comes with a number of options, we're using the options which are absolutely necessary for 
generating the client code.
* `-i` the URL pointing to your application's `Swagger api docs`.
* `-l` the programming language of the client which in this case is `java`
* `-o` the output folder for the generated client code.

Once you execute the previous command for generating the code, you should notice notice the following message on your
terminal:

```
[main] INFO io.swagger.parser.Swagger20Parser - reading from http://localhost:8080/v2/api-docs?group=employee
[main] WARN io.swagger.codegen.ignore.CodegenIgnoreProcessor - Output directory does not exist, or is inaccessible. No file (.swagger-codegen-ignore) will be evaluated.
[main] INFO io.swagger.codegen.AbstractGenerator - writing file swagger-codegen-client/src/main/java/io/swagger/client/model/Employee.java
[main] INFO io.swagger.codegen.AbstractGenerator - writing file swagger-codegen-client/docs/Employee.md
[main] INFO io.swagger.codegen.AbstractGenerator - writing file swagger-codegen-client/src/main/java/io/swagger/client/api/EmployeeControllerApi.java
...
[main] INFO io.swagger.codegen.AbstractGenerator - writing file swagger-codegen-client/src/main/java/io/swagger/client/ApiClient.java
...
```

## REST Client Project
Once the code generation is completed, you should notice a `gradle` project with the following structure:

```
__ swagger-codegen-client
  |__ README.md
  |__ build.gradle
  |__ build.sbt
  |__ docs
  |__ git_push.sh
  |__ gradle
  |__ gradle.properties
  |__ gradlew
  |__ gradlew.bat
  |__ pom.xml
  |__ settings.gradle
  |__ src
     |__ main
        |__ java
          |__ io.swagger.client.api
             |__ EmployeeControllerApi.java
     |__ test
        |__ java
          |__ io.swagger.client.api
             |__ EmployeeControllerApiTest.java
```

# Using the REST Client
The client project contains lot of java classes. However the most important class is the `EmployeeControllerApi.java`. 
This's the class which contains all the logic for making REST client classes.

The other important class is `EmployeeControllerApiTest.java`. It shows you how to use the ``EmployeeControllerApi.java`.
The generated client project also provides a `README` file which can be very helpful.

## URL Changes
The `ApiClient` class contains information related to establishing a HTTP client connection. Please make sure the `basePath`
to your REST application is correct. In the generated example, the `basePath` had a `https://localhost:8080` URL 
instead of `http://localhost:8080`.
    
## Example REST Call
Here's an example of creating an `employee` by making a REST POST method method call.

```
Employee employee = new Employee();
employee.setId(3L);
employee.setFirstName("Sam");
employee.setLastName("Fox");
employee.setEmail("sfox@gmail.com");
Employee response = api.createEmployeeUsingPOST(employee);
System.out.println(response);
```

You should a response similar to this:

```
class Employee {
    email: sfox@gmail.com
    firstName: Sam
    id: 3
    lastName: Fox
}
```


