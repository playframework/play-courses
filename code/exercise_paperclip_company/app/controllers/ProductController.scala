package controllers

import javax.inject.Inject

import models.Product
import play.api.i18n.*
import play.api.mvc.{Action, AbstractController, ControllerComponents, Request, MessagesRequest, AnyContent}
import play.api.data.Form
import play.api.data.Forms.*

class ProductController @Inject() (cc: ControllerComponents) extends AbstractController(cc) with I18nSupport {
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
      "description" -> nonEmptyText,
    )(Product.apply)(Product.unapply)
  )

  private var products = Product.findAll

  def listProducts = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.products.listProducts(products.toSeq, productForm))
  }

  def show(ean: Long) = Action { implicit request: Request[AnyContent] =>
    Product.findByEan(ean).map(product =>
      Ok(views.html.products.details(product))
    ).getOrElse(NotFound)  
  }

  def createProduct = Action { implicit request: Request[AnyContent] =>
    val errorFunction = { (formWithErrors: Form[Product]) =>
      BadRequest(views.html.products.listProducts(products.toSeq, formWithErrors))
    }

    val successFunction = { (data: Product) =>
      val product = Product(data.ean, data.name, data.description)
      products += product

      // Todo: add i18n messages
      Redirect(routes.ProductController.listProducts()).flashing("info" -> "Product Added!")
    }

    productForm.bindFromRequest().fold(errorFunction, successFunction)
  }

  private def isEanExisting(ean: Long): Boolean = {
    Product.findByEan(ean).isEmpty
  }
}
