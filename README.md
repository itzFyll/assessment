# How to Run

## 0- Prerequises

* Java SDK v21 : https://www.oracle.com/ca-en/java/technologies/downloads/#jdk21-windows
* Your google account must be added in GCP test users
    * Currently added users: karim.sacre@aodocs.com
    * Request repo owner to be added.

## 1- Run the Backend Server
From within the aodoc-assesment-app folder run the command `./gradlew.bat build`
Is not installed, gradle will install itself
If this Gradle result in error, make sure Java 21 is used, `java.exe --version` (Change JAVA_HOME env variable to point to installed v21)
Run the BE: `gradlew.bat bootRun`

## 2- Get the JWT Token

The app doesn't have an API and database for Users yet, therefore a new JWT is provided in the console on boot. Each token is valid for 1h.
Check the console where the project was run, `ce.JwtService: <JWT-token>`

## 3- Using a REST API client (ex.: Postman)

POST localhost:8080/test/sheets
Optional (return 401 if not present) Auth Type (Header) : Bearer Token `<JWT-token>`
Optioan Body (json): {"email": "foo@bar.com"}

Note: Email format is validated, will return 400 Bad Request if wrong.

