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

  def list = Action { implicit request: Request[AnyContent] =>
    val resultProducts: List[Product] = Product.findAll
    Ok(views.html.products.listProducts(resultProducts.toSeq))
  }

  def show(ean: Long) = Action { implicit request: Request[AnyContent] =>
    val resultProduct: Option[Product] = Product.findByEan(ean)
    resultProduct match {
      case Some(value) => Ok(views.html.products.details(value))
      case None        => BadRequest
    }

  }
}
