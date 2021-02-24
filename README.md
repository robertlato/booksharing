# Booksharing
> Book sharing system for supporting and analysis of sharing and reading books from private resources, providing information about users, their collections and locations. Developed as a group project as part of classes at university.
The project consists of two parts: front and back-end.

## Key Features
* Creating "sharepoints" where You can add books from Your private collection, which You would like to share with others
* Books borrowing feature
* Searching for the books and spotting the results on the map

## Technologies
* Java 11
* Spring Boot 2.4.1
* Node.js
* ReactJS 17.0.1
* OpenLayers 6.5.0
 
## How To Use
This project can run on Windows, Linux and MacOS (every environment with Node.js, Java 11 and web browser installed).
To clone and run this application, you'll need [Git](https://git-scm.com) (or download the app directly), [Node.js](https://nodejs.org/en/download/) (which comes with [npm](http://npmjs.com)) and Your favourite Java IDE installed on your computer.
* From Your IDE import the project (Booksharing folder), install dependencies with Gradle and run.
* From Your command line:

```bash
# Clone this repository
$ git clone https://github.com/robertlato/booksharing.git

# Go into the repository
$ cd booksharing-front/

# Install dependencies
$ npm install

# Run the app
$ npm start
```
You can check the app by typing [http://localhost:3000/](http://localhost:3000/) in Your web browser. 

## Database
The embedded H2 database is used in the project. You can manage database by a web browser. To do that:
* Go to [http://localhost:8889/h2/](http://localhost:8889/h2/)
* In login form change JDBC url to: `jdbc:h2:file:../booksharingDB`
* Type in credentials: 
    * default username: sa 
    * default password: password

You can always change database configuration in `application.properties` file (/Booksharing/src/main/resources)

## Status
Project is continued as an engineering diploma thesis



