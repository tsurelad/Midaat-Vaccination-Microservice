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

Examples:

        curl -X GET -u user:user "http://localhost:8080/vaccinations"
        curl -X POST -u admin:admin -H 'Content-Type: application/json' -d '{"Name":"My New Vaccination"}' "http://localhost:8080/vaccinations"

If you want (and you do...) a better security - change the password for those users in the DB. The two relevant tables are `authorities` for managing the user roles, and `users` for managing usernames and passwords.

## Admin interface
Go to `/admin.html` (e.g. [localhost](http://localhost:8080/admin.html)) to edit the DB models. You can leave the "server" field empty, and it'll use itself as the current server. You must use an admin user.

## Deploy to Heroku
If you want to deploy your project on heroku, just sign up [here](https://www.heroku.com/), download the [heroku toolbelt](https://toolbelt.heroku.com/), and then run:

        heroku create a-good-and-available-name
        heroku addons:add heroku-postgresql:hobby-dev
        git push heroku master

And now you have an SSL protected microservice up and running.

## API Documentation
Most of the API is built using the [HATEOAS Rest Standard](http://en.wikipedia.org/wiki/HATEOAS). You can go to the main URL to view the available resources:

        curl -X GET -u user:user "https://vaccination.herokuapp.com/"

and you'll get something similar to:

        {
            "_links": {
                "vaccinationDates": {
                    "href": "https://vaccination.herokuapp.com/vaccinationDates{?page,size,sort}",
                    "templated": true
                },
                "compoundInVaccinationDates": {
                    "href": "https://vaccination.herokuapp.com/compoundInVaccinationDates{?page,size,sort}",
                    "templated": true
                },
                "compounds": {
                    "href": "https://vaccination.herokuapp.com/compounds{?page,size,sort}",
                    "templated": true
                },
                "users": {
                    "href": "https://vaccination.herokuapp.com/users{?page,size,sort}",
                    "templated": true
                },
                "vaccinations": {
                    "href": "https://vaccination.herokuapp.com/vaccinations{?page,size,sort}",
                    "templated": true
                },
                "vaccinationInCompounds": {
                    "href": "https://vaccination.herokuapp.com/vaccinationInCompounds{?page,size,sort}",
                    "templated": true
                },
                "authorities": {
                    "href": "https://vaccination.herokuapp.com/authorities{?page,size,sort}",
                    "templated": true
                },
                "profile": {
                    "href": "https://vaccination.herokuapp.com/alps"
                }
            }
        }

