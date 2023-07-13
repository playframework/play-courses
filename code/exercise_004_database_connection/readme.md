# Connecting to a database

## Tasks

We now want to persist our data in a safe place. For that, we need to add a database to our application. For an application with a basic model, we can choose a SQL database like postgresql.

For simplicity reason, we already made a `docker-compose.yml` file to setup our database and create the initial table with some dummy data. To start it up, run: `docker-compose up`.

We also made our application aware of the database by adding the credentials & the name in the application configuration file (`application/conf`).

## Steps

### Interact with our database using anorm

Anorm is a simple SQL data access. It uses play SQL to talk to database and help with parsing the returned data. It is a good mix between a fully featured ORM (Object Relational Mapper) and Jdbc (Java DataBase Connector). Writing plain SQL might seems odd, but it actually allows to remove an abstraction layer and avoid a lot of overhead. SQL is already a perfect DSL (Domain Specific Language) to talk to your data.

Add the anorm librairy to the `build.sbt` file as well as the postgresql connector.

### Retrieving the data

Go into the product model file, this is were we are going to retrieve our data.

For that, we'll update multiple methods to use the connection to our database rather than the dummy implementation used earlier.

```scala
def getAll: List[Product]
def getByEan: Option[Product]
def insert(product: Product): Option[Long]
def update(ean: Long): Int
def delete(ean: Long)
```

Hint: In anorm, you will need to create a parser for the row in your database and the SQL query associated with your model. They usually look like this:

```scala
// Row parser
val productParser: RowParser[Product] =
    long("ean") ~ str("name") ~ str("description") map:
      case ean ~ name ~ description => Product(ean, name, description)

// query
val selectProducts: SqlQuery = SQL("Select ean, name, description from products order by ean asc")
```

You can refer to the [anorm documentation](https://playframework.github.io/anorm) for further instruction.

### Passing the date to the controller

In our `ProductController`, we can now use our new methods to get the data we need. Because we didn't change our model, this should be trivial to do.
