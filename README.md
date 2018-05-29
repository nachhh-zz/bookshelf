# bookshelf

How to start the bookshelf application
---

1. Run `ng build --prod` to build ui code. 
1. Run `sqlite3 bookshelf.db < create_db.sql` to generate sqlite DB structure
1. Run `mvn clean install` to build your application
1. Start application with `java -jar target/bookshelf-1.0-SNAPSHOT.jar server config.yml`
1. To check that your application is running enter url `http://localhost:8080`

Health Check
---

To see your applications health enter url `http://localhost:8081/healthcheck`
