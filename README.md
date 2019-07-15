# Demo WebShop
Simple web shop REST api using Spring Boot

## Overview

The goal of this project is to show how to use [Spring Boot][1] to build a simple REST API.

## Building

You will need Java 11 or later to build. It is build using [Maven][2]:

```
./mvnw clean install
```
## API Documentation

The API documentation is build using [Spring REST Docs][3] and is accessible after the project is build in 
```
/target/generated-docs/api-guide.html 
```

## Security

The api is not secured. How the app is secured depends on how it is used. If the the service is called 
from another service then the requests should be protected by some kind of authentication. 

If the service 
is used by the end user it must be ensured that the end user cannot tamper with the requests to get the 
goods for free by changing the product price in the order object. This can be solved by adding a [HMAC][4]
to the product response of create and list products:

```
{
    "id": 1,
    "name": "Red Wine",
    "price" :99.95,
    "mac": "78687JHGJHGJHG87675454213KJHGKJHG"
}
```
This HMAC must then be returned when making calls to create order:

```
{
    "email": "bob@gmail.com",
    "orderLines":
        [{
            "amount": 1,
            "product":
            {
                "id": 1,
                "name": "Red Wine",
                "price": 100,
                "mac": "78687JHGJHGJHG87675454213KJHGKJHG"
            }
        }],
    "totalPrice": 100
}
```  


[1]: https://spring.io/projects/spring-boot
[2]: https://maven.apache.org/
[3]: https://spring.io/projects/spring-restdocs
[4]: https://en.wikipedia.org/wiki/HMAC