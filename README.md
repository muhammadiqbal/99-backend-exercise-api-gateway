**Public APIs**

This Public APIs service is part of the 99 backend challenge. This API is built  
using Spring boot framework. 

**Architecture** 
                
            Clients (Web, mobile etc.)
                    |
                   [REST]
                Public APIs
        ____________|____________
        |                       |
      [REST]                 [REST] 
        |                       |       
    ListingService            UserService
        |                       |
        |                       |
    Listing DB                  User DB
     (sqlite)                   (H2 imemory DB)



**How to run**

     ./mvnw clean compile -U

    mvn spring-boot:run

The service is assumed to be run locally http://localhost:8080


**Manual testing result**
![create user.png](create%20user.png)

![create listing.png](create%20listing.png)

![get listings.png](get%20listings.png)