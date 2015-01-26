# Midaat-Vaccination-Microservice
A Vaccination table microservice to manage the vaccination information.


## Get & build the code
Clone the repository, and build it using maven and java 8:

        git clone https://github.com/tsurelad/Midaat-Vaccination-Microservice.git
        cd Midaat-Vaccination-Microservice
        mvn clean install
        java -jar target/vaccination-table-service-0.0.1-SNAPSHOT.jar

## Use different DBs
The default behaviour is to run the microservice with embedded [H2](http://www.h2database.com/html/main.html) database, which is in-memory and thus gets deleted upon closing the microservice.
If you need a persistent DB, you can run the project with `mysql` or `postgres` spring profiles, using the [mysql](http://www.mysql.com/) or [PostgreSQL](http://www.postgresql.org/) DBs.
Example for running the microservice with MySQL:

        java -Dspring.profiles.active=mysql -jar target/vaccination-table-service-0.0.1-SNAPSHOT.jar

The DB tables are created automatically for you if they are not there when the microservice is loaded.

## Users
The microservice requires a login to view/change the data. The default user with read actions is `user` (with password `user`), and the admin user enabling editing is `admin` (with password `admin`).