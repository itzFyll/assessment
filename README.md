# Description

This is an assessment mini-project, made within ~6h.

Initial technical requirements:
You are tasked with building a Java application that interacts with the Google Sheets API. Your script should copy a Google Sheet, add rows, highlight colors and delete duplicate data. Use Spring Boot framework. Here are the tasks for the assignment. Utilize the Google Sheets API to interact with a Google Sheets spreadsheet.
Create a GCP project or use an existing project that you might have. 
Create a API credentials in order to generate an access token with your user account to connect to the Google APIs
Write an API endpoint:
   Path: `/test/sheets`
   Method: POST
   Headers:
   Authorization: Bearer <jwt token>
   Body (json)
   { "email": "" }
Endpoint should first check if the JWT token passed in the header is valid
   Check if the token is a valid JWT
   Check if the JWT is not expired
   If not valid throw a 401 error
   Use the following library com.auth0.jwt.JWT to validate the JWT.
Copy the following spreadsheet: https://docs.google.com/spreadsheets/d/1hLgSCS0VbEbkkhkeFLFdzzUrs4nD9eJ5KFHBVJZmD1I/edit#gid=0
Use the copied spreadsheet to make the below changes
   Note: You can use the Drive and Google Sheets Java SDK client to connect to the Google APIs 
In the new copied spreadsheet
   Add a background color (Blue) on all the cells that have country "United States"
   Add a new row with the following details in each of the columns:
      Name: AODocs
      Region: California
      Country: United States
      Phone: (310) 310-1234
   Delete all duplicate rows (duplicates are identified by the Name column so delete any 2 rows that have the same name). [In the spreadsheet I highlighted in yellow the ones that are duplicates, so the script should   ensure that there are no duplicate rows.]
   Share the spreadsheet with the email passed in the API body. If no email is passed, then don't share the file. 
Make sure to add a README.md file that has the steps on how to run the project and any assumptions that you had to take. 

# How to Run

## 0- Prerequises

* Java SDK v21 : https://www.oracle.com/ca-en/java/technologies/downloads/#jdk21-windows
* Your google account must be added in GCP test users
    * Currently added users: ka****@aodocs.com // trunked for confidentiality
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

