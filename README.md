# Getting Started
This project is set up with Spring Boot and configured to use PostgreSQL.

### Installation and configuration for PostgreSQL
1. [Download](https://www.enterprisedb.com/downloads/postgres-postgresql-downloads) and install PostgreSQL
2. When prompted, install the relevant JDBC drivers use the following configuration:
   - Database port: `5432`
   - Username: `postgres`
   - Password: `password`
3. Create a database with name `oppenheimer`

If you have set any of the configurations differently for steps 2 or 3, you can set the app to use them in file `src/main/resources/application.properties`

### Launch App
Simply launch the application using your favourite IDE (additional configurations might be required to set up your editor to run this Spring Boot app).

User accounts are configured to be automatically created in-memory on app launch for quickstart purposes. See file `src\main\java\com\cds\oppenheimer\config\OppenHeimerConfiguration.java` for login credentials.

### Accessing RESTful API endpoints
API endpoints have been configured with Spring Security to restrict access to the relevant user roles.

To authenticate requests, use `Basic Auth` with the login credentials. This can be easily performed using tools like [Postman](https://www.getpostman.com/downloads/) or [Insomnia](https://insomnia.rest/download/).

Refer to the controller files in folder `src\main\java\com\cds\oppenheimer\controller\api` for defined API routes.