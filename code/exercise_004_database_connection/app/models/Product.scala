package models

import java.util.Date

import anorm.{
  RowParser,
  ~,
  SqlQuery,
  SQL,
  as,
  ResultSetParser,
  on,
}
import anorm.SqlParser.{long, str, int}
import javax.inject.Inject
import play.api.db.Database
import java.sql.Connection
import scala.concurrent.Future

case class Product(
  ean: Long,
  name: String,
  description: String,
)

object Product {
  // custom unapply method for the Play Form object, needed to compile in Scala3
  def unapply(product: Product): Option[(Long, String, String)] = {
    Some((product.ean, product.name, product.description))
  }
}

class ProductDao @Inject()(database: Database) {

  val selectProducts: SqlQuery = SQL("Select ean, name, description from products order by ean asc")

  val selectProductByEan: SqlQuery = SQL("Select ean, name, description from products where ean = {ean}")

  val insertProduct: SqlQuery = SQL(
    """
      Insert into products (ean, name, description)
      values ({ean}, {name}, {description})
    """
  )

  val updateProductByEan: SqlQuery = SQL(
    """
      Update products
      set
        ean = {new_ean},
        name = {new_name},
        description = {new_description}
      where
        ean = {ean}
    """
  )

  val deleteProductByEan: SqlQuery = SQL(
    """
      Delete from products
      where ean = {ean}
    """
  )

  val productParser: RowParser[Product] =
    long("ean") ~ str("name") ~ str("description") map:
      case ean ~ name ~ description => Product(ean, name, description)

  val productsParser: ResultSetParser[List[Product]] =
    productParser.*

  def getAll: List[Product] =
    database.withConnection { implicit c: Connection =>
      selectProducts.as(productsParser)
  }

  def getByEan(ean: Long): Option[Product] =
    database.withConnection { implicit c: Connection =>
      selectProductByEan.on("ean" -> ean).as(productParser.singleOpt)
  }

  def insert(product: Product): Option[Long] =
    database.withConnection { implicit c: Connection =>
      insertProduct.on(
        "ean" -> product.ean,
        "name" -> product.name,
        "description" -> product.description,
      ).executeInsert()
    }

  def update(ean: Long, product: Product): Int =
    database.withConnection { implicit c: Connection =>
      updateProductByEan.on(
        "new_ean" -> product.ean,
        "new_name" -> product.name,
        "new_description" -> product.description,
        "ean" -> ean,
      ).executeUpdate()
    }

  def delete(ean: Long) =
    database.withConnection { implicit c: Connection =>
      deleteProductByEan.on(
        "ean" -> ean,
      ).executeUpdate()
    }
}
