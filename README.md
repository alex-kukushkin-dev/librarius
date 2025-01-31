# Library Application #
A simple full-stack Library Application 
built with following stack 
### for backend : ### 
+ Java 21 LTS
+ Spring Boot 3.4.2 
+ H2 embedded database
+ Liquibase for data migration  

### for frontend tech stack used : ###
+ Angular 19
+ TypeScript 5.7.3
+ HTML5
+ CSS3

### By using Library App Users can: ###

+ Add a new book to the catalog
+ Borrow a book
+ Return a book
+ Print a list of the books that user has borrowed

+ Additionally, there is a Login page (simple username-based login), basic CRUD for Users and Books, plus filtering and pagination for books.

## Steps to start the backend module : ##
1. Navigate to `backend` directory
2. Run: `mvn spring-boot:run`
3. The Spring Boot application will spin-up on default port 8080

## Steps to start the frontend app : ##
1. Navigate to `frontend` directory
2. Install dependencies: `npm install`
3. Start dev server with: `npm start`
   This runs `ng serve --open --proxy-config src/proxy.conf.json`, 
   opening http://localhost:4200 in your browser automatically


## Application Usage ##
+ Visit http://localhost:4200.
+ Login Page:
  Enter a username (e.g., “admin”).
  If the user does not exist in the DB, 
  the backend will create it.
  On success, you are redirected to the Books page.
+ Books Page:
Shows a paginated list of books from the DB.
You can search by title/author using the search bar.
+ Borrow or Return (if you’re logged in).
Click “Add New Book” to create a new book.
Click “Edit” to modify an existing book.
+ Users Page:
Displays a list of all users in the system.
+ Borrowed Books:
Press “Load Borrowed Books” to see 
which books your logged-in user has borrowed.
+ Logout:
Click “Logout” in the top navigation 
to clear your logged session (stored in localStorage) 
and return to the login page.

## H2 Console ##
If you want to inspect or debug the data, 
go to http://localhost:8080/h2-console.
* Use the default JDBC URL: jdbc:h2:mem:librarydb.
* Credentials: 
  username: admin,
  password: admin 
(as configured in application.properties)
