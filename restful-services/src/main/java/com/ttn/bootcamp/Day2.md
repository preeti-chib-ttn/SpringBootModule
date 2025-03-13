# Spring Boot RESTful Service - Day 2

## 1. Internationalization
### A) Add support for Internationalization
- Configure the application to support messages in **English (default), German, and Swedish**.

### B) Create a GET Request for Localized Greeting
- Implement a GET request that takes `"username"` as a parameter and returns a localized message:  
  **"Hello, Username"** (using message properties).
- ![Internationalization.png](../../../../resources/Output/Day2/Internationalization.png)

## 2. Content Negotiation
### A) Create a POST Method for User Creation
- Implement a POST method that **accepts XML** input to create user details.
- ![Xml.png](../../../../resources/Output/Day2/Xml.png)

### B) Create a GET Method to Fetch Users in XML
- Implement a GET method that **returns the list of users in XML format**.
- ![XmlAll.png](../../../../resources/Output/Day2/XmlAll.png)

## 3. Swagger Configuration
### A) Configure Swagger and Document APIs
- Configure the **Swagger plugin** and generate API documentation for the following methods:
    - **GET**: Fetch user details
    - **POST**: Save user details
    - **DELETE**: Remove a user
  
- ![AllUserApi.png](../../../../resources/Output/Day2/AllUserApi.png)

### B) Enhance Swagger Documentation
- Add **descriptions** for each class and URI to clarify their purpose in Swagger UI.
- ![Swagger Demo.png](../../../../resources/Output/Day2/Swagger%20Demo.png)


## 4. Static and Dynamic Filtering
### A) Static Filtering
- Create an API that **saves user details (including password)** but returns only **non-sensitive data** in the response.

- ![Password.png](../../../../resources/Output/Day2/Password.png)

### B) Dynamic Filtering
- Implement another API that does the **same filtering dynamically** based on conditions.
- ![DynamicDetails.png](../../../../resources/Output/Day2/DynamicDetails.png)

## 5. Versioning RESTful APIs
- Create **two APIs** to return user details:
    - One returns **basic user details**.
    - Another returns **enhanced user details**.

### Apply API Versioning Using:
A) **MimeType Versioning**  
- ![MimeTypeV1.png](../../../../resources/Output/Day2/MimeTypeV1.png)
- ![MimeTypeV2.png](../../../../resources/Output/Day2/MimeTypeV2.png)

---
B) **Request Parameter Versioning**
- ![RequestParamV1.png](../../../../resources/Output/Day2/RequestParamV1.png)
- ![RequestParamV2.png](../../../../resources/Output/Day2/RequestParamV2.png)
---
C) **URI Versioning**  
- ![Uri version v1.png](../../../../resources/Output/Day2/Uri%20version%20v1.png)
- ![URIVersionV2.png](../../../../resources/Output/Day2/URIVersionV2.png)
---
D) **Custom Header Versioning**
- ![HeaderVersionV1.png](../../../../resources/Output/Day2/HeaderVersionV1.png)
- ![HeaderVersion2.png](../../../../resources/Output/Day2/HeaderVersion2.png)
---
## 6. HATEOAS
### A) Configure HATEOAS in Spring Boot
- Implement an API that **returns user details along with links** to show all topics dynamically.  
- ![Hateos.png](../../../../resources/Output/Day2/Hateos.png)