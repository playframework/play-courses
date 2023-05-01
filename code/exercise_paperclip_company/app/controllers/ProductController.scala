package controllers

import play.api.i18n._
import javax.inject.Inject
import play.api.mvc.{Action, AbstractController, ControllerComponents, Request, MessagesRequest, AnyContent}
import models.Product
import play.api.data.Form
import play.api.data.Forms.*

class ProductController @Inject() (cc: ControllerComponents) extends AbstractController(cc) with I18nSupport {
  private var products = Product.findAll

  private var products2 = scala.collection.mutable.ArrayBuffer(
    Product(5010255079763L, "Paperclips Large",
      "Large Plain Pack of 1000"),
    Product(5018206244666L, "Giant Paperclips",
      "Giant Plain 51mm 100 pack"),
    Product(5018306332812L, "Paperclip Giant Plain",
      "Giant Plain Pack of 10000"),
    Product(5018306312913L, "No Tear Paper Clip",
      "No Tear Extra Large Pack of 1000"),
    Product(5018206244611L, "Zebra Paperclips",
      "Zebra Length 28mm Assorted 150 Pack"),
    Product(7809247890124L, "Test Test",
      "This is a test product to ensure that I am using the correct variable"),
  )

  def list = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.list(products2, productForm))
  }

  def show(ean: Long) = Action { implicit request: Request[AnyContent] =>
    Product.findByEan(ean).map(product =>
      Ok(views.html.products.details(product))
    ).getOrElse(NotFound)  
  }

  def createProduct = Action { implicit request: Request[AnyContent] =>
    val errorFunction = { (formWithErrors: Form[Product]) =>
      BadRequest(views.html.list(products2, productForm))
    }

    val successFunction = { (data: Product) =>
      val product = Product(data.ean, data.name, data.description)
      // products2 += product

      Redirect(routes.ProductController.show(product.ean))
    }

    productForm.bindFromRequest().fold(errorFunction, successFunction)
  }

  def validateEan(ean: Long): Boolean = {
    Product.findByEan(ean).isEmpty
  }

  val productForm: Form[Product] = Form(
    mapping(
      "ean" -> longNumber.verifying(
        "validation.ean.duplicate",
        fields =>
          fields match {
            case productEan => validateEan(productEan)
          }
      ),
      "name" -> nonEmptyText,
      "description" -> nonEmptyText,
    )(Product.apply)(Product.unapply)
  )
}
