# EmployeeControllerApi

All URIs are relative to *https://localhost:8080*

Method | HTTP request | Description
------------- | ------------- | -------------
[**createEmployeeUsingPOST**](EmployeeControllerApi.md#createEmployeeUsingPOST) | **POST** /api/v1/employees | Add an employee
[**deleteEmployeeUsingDELETE**](EmployeeControllerApi.md#deleteEmployeeUsingDELETE) | **DELETE** /api/v1/employees/{id} | Delete an employee
[**getAllEmployeesUsingGET**](EmployeeControllerApi.md#getAllEmployeesUsingGET) | **GET** /api/v1/employees | View a list of available employees
[**getEmployeeByIdUsingGET**](EmployeeControllerApi.md#getEmployeeByIdUsingGET) | **GET** /api/v1/employees/{id} | Get an employee by Id
[**updateEmployeeUsingPUT**](EmployeeControllerApi.md#updateEmployeeUsingPUT) | **PUT** /api/v1/employees/{id} | Update an employee


<a name="createEmployeeUsingPOST"></a>
# **createEmployeeUsingPOST**
> Employee createEmployeeUsingPOST(employee)

Add an employee

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.EmployeeControllerApi;


EmployeeControllerApi apiInstance = new EmployeeControllerApi();
Employee employee = new Employee(); // Employee | Employee object store in database table
try {
    Employee result = apiInstance.createEmployeeUsingPOST(employee);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling EmployeeControllerApi#createEmployeeUsingPOST");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **employee** | [**Employee**](Employee.md)| Employee object store in database table |

### Return type

[**Employee**](Employee.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

<a name="deleteEmployeeUsingDELETE"></a>
# **deleteEmployeeUsingDELETE**
> Map&lt;String, Boolean&gt; deleteEmployeeUsingDELETE(id)

Delete an employee

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.EmployeeControllerApi;


EmployeeControllerApi apiInstance = new EmployeeControllerApi();
Long id = 789L; // Long | Employee Id from which employee object will delete from database table
try {
    Map<String, Boolean> result = apiInstance.deleteEmployeeUsingDELETE(id);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling EmployeeControllerApi#deleteEmployeeUsingDELETE");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **Long**| Employee Id from which employee object will delete from database table |

### Return type

**Map&lt;String, Boolean&gt;**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="getAllEmployeesUsingGET"></a>
# **getAllEmployeesUsingGET**
> List&lt;Object&gt; getAllEmployeesUsingGET()

View a list of available employees

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.EmployeeControllerApi;


EmployeeControllerApi apiInstance = new EmployeeControllerApi();
try {
    List<Object> result = apiInstance.getAllEmployeesUsingGET();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling EmployeeControllerApi#getAllEmployeesUsingGET");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

**List&lt;Object&gt;**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="getEmployeeByIdUsingGET"></a>
# **getEmployeeByIdUsingGET**
> Employee getEmployeeByIdUsingGET(id)

Get an employee by Id

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.EmployeeControllerApi;


EmployeeControllerApi apiInstance = new EmployeeControllerApi();
Long id = 789L; // Long | Employee id from which employee object will retrieve
try {
    Employee result = apiInstance.getEmployeeByIdUsingGET(id);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling EmployeeControllerApi#getEmployeeByIdUsingGET");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **Long**| Employee id from which employee object will retrieve |

### Return type

[**Employee**](Employee.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="updateEmployeeUsingPUT"></a>
# **updateEmployeeUsingPUT**
> Employee updateEmployeeUsingPUT(employeeDetails, id)

Update an employee

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.EmployeeControllerApi;


EmployeeControllerApi apiInstance = new EmployeeControllerApi();
Employee employeeDetails = new Employee(); // Employee | Update employee object
Long id = 789L; // Long | Employee Id to update employee object
try {
    Employee result = apiInstance.updateEmployeeUsingPUT(employeeDetails, id);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling EmployeeControllerApi#updateEmployeeUsingPUT");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **employeeDetails** | [**Employee**](Employee.md)| Update employee object |
 **id** | **Long**| Employee Id to update employee object |

### Return type

[**Employee**](Employee.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

