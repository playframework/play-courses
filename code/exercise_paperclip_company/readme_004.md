# Connecting to a database

We now want to persist our data in a safe place. For that, we need to add a database to our application. For an application with a basic model, we can choose a SQL database like postgresql.

For simplicity reason, we already made a `docker-compose.yml` file to setup our database and create the initial table with some dummy data. To start it up, run: `docker-compose up`.

We also made our application aware of the database by adding the credentials & the name in the application configuration file (`appliction/conf`).

## Interact with our database using anorm

Anorm is a simple SQL data access. It uses play SQL to talk to database and help with parsing the returned data. It is a good mix between a fully featured ORM (Object Relational Mapper) and Jdbc (Java DataBase Connector). Writing plain SQL might seems odd, but it actually allows to remove an abstraction layer and avoid a lot of overhead. SQL is already a perfect DSL (Domain Specific Language) to talk to your data.

Add the anorm librairy to the `build.sbt` file as well as the postgresql connector.

## Retrieving the data

Go into the product model file, this is were we are going to retrieve our data. For that, 

