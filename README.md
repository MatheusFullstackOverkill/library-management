# Library Management

## About project
A library management in Java to run in the terminal.
The purpose of this project was to exercise Java main concepts like OOP and technologies used with the language.

### Features
- Login;
- Add. book title;
- Add. book copy;
- Borrow books;
- Return books;
- Loggout.

## Technologies
- Java
    - JDBC
    - Hibernate
- Maven
- Environment Variables

## Project requirements
- A JDK version 21 or 25;

## Run project steps
- Run the docker compose file or create a PostgreSQL database by yourself;
- Run the scripts in the `src/main/resources/database.sql` file in the database;
- Create a user in database with usertype "admin", the password it's not hashed;
- Copy the `.env.example` file to an `.env` file and set the environment variables;
- In the IDE of your choice, run the project.