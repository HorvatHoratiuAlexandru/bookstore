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

3. Create a .env file in the root directory using the example.env file as a template.
4. Create secrets.properties using the secrets.example.properties file located at bookstore/src/main/resources.
5. In application.properties set seeding to true if you want to seed the db with some books
6. Build the jar file
   ```bash
   mvn clean package -DskipTests
   ```
7. Run docker-compose
   ```bash
   docker-compose up
   ```

## Note:

To run locally just change the mysql.host from "db" to "localhost" and remove or comment out the 'app' service from the docker-compose file.

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
  - ✅: delete a review from a book
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
- Custom AOP aspect that verifies if the request user UID matches the logged-in user UID.
- Multipart file upload
- Global Exception Handling

## TODO:

- Tests
- Google auth
- K8s
- Cloud deployment on GoogleKubernetesEngine
- User email validation

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

**The password must be at least 8 characters long and contain at least one uppercase letter, one number, and one special symbol.**

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

Note: The user creating a book needs ADMIN privileges to do so:

- Register a user
- Connect to the mysql db using a SQL client like [DBeaver](https://dbeaver.io/) or SSH into the mysql container using

```Bash
Step 1: docker exec -it {container-name} /bin/bash
Step 2: mysql -u {mysql username or root} -p
```

- Update the desired user role to admin

```SQL
UPDATE  users
SET role = 1
WHERE users.id = {#user_id};
```

- Re-login the user so the JWT token now has role admin

---

**Let say we want to add "Lord of the Rings Test"**

---

**First**: upload one or more images and get the uploaded images links

**POST: localhost:8080/admin/book-image**

req body: multipart form data

response:

```JSON
[
    {
        "link": "r0EiVGqB53o9*Eaq2$dEwqBd.jpg"
    },
    {
        "link": "1QRRV&bOWnAxF3*iF4K&RQ5Z.jpg"
    }
]
```

---

**Second**: we search for tag(s)

**GET: localhost:8080/admin/search/tag?search=fantasy**

req body:empty

response:

```JSON
[
    {
        "id": 6,
        "suggestion": "FANTASY"
    }
]
```

---

**Third**: we search for existing author(s)

**GET: localhost:8080/admin/search/authors?search=j.r.r.**

req body:empty

response:

```JSON
[
    {
        "id": 59,
        "suggestion": "J.R.R. Tolkien"
    }
]
```

---

**Fourth**: we create the book

**GET: localhost:8080/admin/book**

req body:

```JSON
{
  "title": "Lord of the Rings Test",
  "description": "This is a test book description.",
  "pageNumber": 250,
  "price": 29.99,
  "stock": 100,
  "newAuthors": ["New Test Author"],
  "tags": [6],
  "authors": [59],
  "images": ["r0EiVGqB53o9*Eaq2$dEwqBd.jpg", "1QRRV&bOWnAxF3*iF4K&RQ5Z.jpg"]
}
```

response:

```JSON
{
    "created": true,
    "link": "/book/100"
}
```

---

**The book has been created**

**GET: localhost:8080/book/100**

req body:empty

response:

```JSON
{
    "id": 100,
    "title": "Lord of the Rings Test",
    "description": "This is a test book description.",
    "pageNumber": 250,
    "price": 29.99,
    "stock": 100,
    "grade": "NA",
    "authors": [
        "New Test Author",
        "J.R.R. Tolkien"
    ],
    "tags": [
        "FANTASY"
    ],
    "images": [
        "1QRRV&bOWnAxF3*iF4K&RQ5Z.jpg",
        "r0EiVGqB53o9*Eaq2$dEwqBd.jpg"
    ]
}
```

---

# For further actions, import the postman_collection.json file and run the application
