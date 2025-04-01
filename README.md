This project implements a tokenization service using Spring Boot. The service provides functionality for converting sensitive data, such as credit card numbers, into unique tokens, which can be safely stored without exposing sensitive information.

Technologies Used
Java 17: Programming language.
Spring Boot 3.4.4: Framework used to build the REST API.
Spring Data JPA: For database operations.
H2 Database: In-memory database for testing.
Spring Security: For securing the API endpoints.
Maven: Dependency management and build tool.

Project Structure

![image](https://github.com/user-attachments/assets/0acfbe00-5231-4cd1-95ff-de82b071c486)

API Endpoints
Tokenization
Endpoint: POST /api/tokenization/tokenize
Description: Converts sensitive data into a token.
Request Body:

![image](https://github.com/user-attachments/assets/3cb37ad2-baba-4897-a04e-f70d48af2cc1)

Response:

![image](https://github.com/user-attachments/assets/c36c615e-34f3-459c-b98c-916ea477d863)

Detokenization
Endpoint: POST /api/tokenization/detokenize
Description: Converts a token back into the original sensitive data.
Request Body:

![image](https://github.com/user-attachments/assets/8d9db0be-6242-4b87-9ca8-92ce2b22ef17)

Response:

![image](https://github.com/user-attachments/assets/527ab23d-a802-4874-865a-fa2f58b7775a)

Configuration
Application Properties
The following properties can be set in src/main/resources/application.properties:

properties

![image](https://github.com/user-attachments/assets/a8a859e9-6767-418c-8a20-dd9d3d73dec9)

Running the Application
Clone the repository.

Navigate to the project directory.

Run the application using Maven:

![image](https://github.com/user-attachments/assets/c152904b-6a30-4e86-9af9-184c152ca787)

Access the H2 console at http://localhost:8080/h2-console (credentials: username: sa, password: password).

Testing the API
You can test the API endpoints using Postman or any other API client:

Tokenization:

Send a POST request to http://localhost:8080/api/tokenization/tokenize with the body as shown above.
Detokenization:

Send a POST request to http://localhost:8080/api/tokenization/detokenize with the token directly as a plain number.
Security
The application uses basic authentication for securing the endpoints. You can add the following header in Postman for testing:

Authorization: Basic {base64_encode(username:password)}
User credentials in memory: username: user, password: password.
