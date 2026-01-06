# Library Management

## About project
A library management in Java to run in the terminal.
The purpose of this project was to exercise Java main concepts like OOP and technologies used with the language.

### Features
- Login
- Add. book title
- Add. book copy
- Borrow books
- Return books
- Loggout

## Technologies
- Java
    - JDBC
    - Hibernate
- Maven
- Environment Variables

## Project requirements
- A JDK version 21 or 25

## Run project steps
- Run the docker compose file with the command `sudo docker compose up` or create a PostgreSQL database by yourself
- Copy the `.env.example` file to an `.env` file and set the environment variables
- Copy the `flyway-example.conf` file to an `flyway.conf` file and set the database variables
- Run the migrations with the command `make run_migrations` in the project root
- In the IDE of your choice, run the project
- The email and password for login can be found in the `src/main/resources/V1_2__create_admin_user.sql` file