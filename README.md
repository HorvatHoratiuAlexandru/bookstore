# Bookstore Application

This is a learning project for building a bookstore application using Spring Boot for the backend and React for the frontend.

## How to Start

To get started with the project, follow these steps:

1. Clone the repository:

   ```bash
   git clone https://github.com/your-username/bookstore-app.git
   ```

2. Navigate to project directory

   ```bash
   cd bookstore
   ```

3. Create .env in root from the example.env
4. Create secrets.properties from bookstore/src/main/resources/secrets.example.properties
5. In application.properties set seeding to true if you want to seed the db with some books
6. Start the DB in docker
   ```bash
   docker-compose up
   ```
7. Start the backend application

## TODO:

1. Backend:

- <s> Basic functionalities </s>
- Data validation
- <s>Exception handling instead of just returning null</s>
- Security (jwt token and Google)
- Tests
- Admin functions
- Dockerfile for backend

2. Frontend:

### Frontend tasks have not been initiated.
