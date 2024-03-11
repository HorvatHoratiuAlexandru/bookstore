# Bookstore Application

This is a learning project for building a bookstore application using Spring Boot for the backend and React for the frontend.

### Frontend tasks have not been initiated.

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
5. In application.properties set seeding to true if you want to seed the db with some books (as of now there will be no images added for the books when seeding and works just if you run the app locally)
6. Build the jar file
   ```bash
   mvn clean package -DskipTests
   ```
7. Run docker-compose
   ```bash
   docker-compose up
   ```

## Endpoints

- auth
  - ✅: register
  - ✅: login
  - ✅: token (for renewing the jwt tokens via refresh token)
- user
  - ✅: get user data
  - ✅: update user (name, adress)
- book
  - ✅: get all books (accepts tags parameters for filtering the books based on tags ex. FANTASY)
  - ✅: get one book
  - ✅: search books (does a simple sql SELECT \* FROM book WHERE book.title LIKE %SEARCH_STRING% OR book.description LIKE %SEARCH_STRING%)
- wishlist
  - ✅: add book to wishlist
  - ✅: remove book from wishlist
  - ✅: clear the wishlist
  - ✅: get user wishlist
- promo codes
  - ✅: promo code validation endpoint (it verifies if a promotional code exists/expired/was already used by user)
- orders
  - ✅: place order
  - ✅: pay order
  - ✅: get user orders
  - ✅: get user order
- reviews (It calculates a review grade based on the number of reviews a book has [POOR, FAIR, GOOD, VERY GOOD, EXCELENT])
  - ✅: add a review to a book
  - ✅: update a review to a book
  - ✅: delete a revie to a book
- admin
  - ✅: upload book image (used to pre upload the images before adding a book)
  - ✅: search tags (used to pre fetch available tags from db before book creation)
  - ✅: search authors (used to pre fetch available authors from db before book creation)
  - ✅: add book
  - ✅: update a book price or stock no.
  - ✅: create promo code
  - ✅: change promo code status to expired or !expired
  - ✅: get all promo codes
  - ✅: get ALL orders
  - ✅: set order status (payed, processed, finished)

## Features

- Data persistence using Spring Data Jpa and MYSQL Database
- Data validation
- Custom JWT authentication
- Role based authorization (REGULAR, ADMIN)
- Custom AOP aspect that checks if the request user UID matches the logged in user UID
- Multipart file upload
- Global Exception Handling

## Upcoming Features

- Google auth
- K8s
- Cloud deployment on GoogleKubernetesEngine
- User email validation
- Tests

## User sample workflow

POST: localhost:8080/user/register

req body:

```JSON
{
    "email": "user@email.com",
    "fullName": "test user",
    "password": "Tuser123!",
    "repeatPassword": "Tuser123!"
}
```

The password must be atleast 8 character long and contain ONE UPPER LETTER, ONE NUMBER AND ONE SPECIAL SYMBOl

response:

```JSON
{
    "id": "21d664ab-f609-455a-91b9-2ec93ba2f07a",
    "active": true,
    "registered": true
}
```

---

POST: localhost:8080/user/login
req body:

```JSON
{
    "email": "user@email.com",
    "password": "Tuser123!"
}
```

response:

```JSON
{
    "userId": "21d664ab-f609-455a-91b9-2ec93ba2f07a",
    "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJcImhvcnZhdGJvb2tzdG9yZVwiIiwiaWQiOiIyMWQ2NjRhYi1mNjA5LTQ1NWEtOTFiOS0yZWM5M2JhMmYwN2EiLCJ1c2VybmFtZSI6ImFsZXh0ZXN0MEBtYWlsLmNvbSIsIm5hbWUiOiJhbGV4dGVzdDAiLCJyb2xlIjoiUkVHVUxBUiIsImlhdCI6MTcxMDE2NTgxNiwiZXhwIjoxNzEwMTY2NzE2fQ.AmadqZt9x9hYV7T2B7Bh0tnAaM_1GuF5ncQuwfHyRRE",
    "refreshToken": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJcImhvcnZhdGJvb2tzdG9yZVwiIiwiaWQiOiIyMWQ2NjRhYi1mNjA5LTQ1NWEtOTFiOS0yZWM5M2JhMmYwN2EiLCJ1c2VybmFtZSI6ImFsZXh0ZXN0MEBtYWlsLmNvbSIsIm5hbWUiOiJhbGV4dGVzdDAiLCJyb2xlIjoiUkVHVUxBUiIsImlhdCI6MTcxMDE2NTgxNiwiZXhwIjoxNzEwMTY4NTE2fQ.JWxYfbijHX7-bFMoWTSI2WJTFFbWJgKnZ7x5fIBG_3s"
}
```

## Create a book

## Place an order

# For the rest import postman_collection.json and run the application
