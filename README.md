# bookshelf

How to start the bookshelf application
---

1. Run `sh deploy.sh` or manually (no docker) foolow next steps:
1. Run `cd src/main/resources/assets/bookshelf && ng build --prod` to build ui code. 
1. Run `sqlite3 bookshelf.db < create_db.sql` from root project to generate sqlite DB structure
1. Run `mvn clean install` from root project to build your application
1. Start application with `java -jar target/bookshelf-1.0-SNAPSHOT.jar server config.yml`
1. To check that your application is running enter url `http://localhost:8080`

REST API
---
1. get all books: `http://localhost:8080/service/books`
1. get book by isbn: `http://localhost:8080/service/books/<isbn>`
1. search terms in title/description: `http://localhost:8080/service/books?keyword=<search terms>`

Health Check
---

To see your applications health enter url `http://localhost:8081/healthcheck`


Missing stuff
---
1. POST /book endpoint that adds a book entry to the bookshelf and adds terms to the inverted index (should filter "wide" terms like pronouns, etc)
1. tables should be in 1st normal form (atomic values) and POJOS should have all fields
1. Could use dropwizard managed objects for the DB manager to help with the life cycle / decoupling 
1. Finish angular FE (just installed)
1. more organized packages
1. put in cloud