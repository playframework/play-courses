package controllers

import javax.inject.Inject

import models.Product
import play.api.i18n.*
import play.api.mvc.{
  Action,
  AbstractController,
  ControllerComponents,
  Request,
  MessagesRequest,
  AnyContent
}
import play.api.data.Form
import play.api.data.Forms.*

import anorm.as

class ProductController @Inject() (
    cc: ControllerComponents,
) extends AbstractController(cc)
    with I18nSupport {
  val productForm: Form[Product] = Form(
    mapping(
      "ean" -> longNumber.verifying(
        "validation.ean.duplicate",
        fields =>
          fields match {
            case productEan => isEanExisting(productEan)
          }
      ),
      "name" -> nonEmptyText,
      "description" -> nonEmptyText
    )(Product.apply)(Product.unapply)
  )

  val editProductForm: Form[Product] = Form(
    mapping(
      "ean" -> longNumber,
      "name" -> text,
      "description" -> text
    )(Product.apply)(Product.unapply)
  )

  def list = Action { implicit request: Request[AnyContent] =>
    val resultProducts: List[Product] = Product.findAll
    Ok(views.html.products.listProducts(resultProducts.toSeq, productForm))
  }

  def show(ean: Long) = Action { implicit request: Request[AnyContent] =>
    val resultProduct: Option[Product] = Product.findByEan(ean)
    resultProduct match {
      case Some(value) => Ok(views.html.products.details(value))
      case None        => BadRequest
    }

  }

  def create = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.products.create(productForm))
  }

  def save = Action { implicit request: Request[AnyContent] =>
    val errorFunction = { (formWithErrors: Form[Product]) =>
      BadRequest(views.html.products.create(formWithErrors))
    }

    val successFunction = { (data: Product) =>
      val product = Product(data.ean, data.name, data.description)
      Product.insert(product)

      // Todo: add i18n messages
      Redirect(routes.ProductController.list())
        .flashing("info" -> "Product Added!")
    }

    productForm.bindFromRequest().fold(errorFunction, successFunction)
  }

  private def isEanExisting(ean: Long): Boolean =
    Product.findByEan(ean).isEmpty
}
