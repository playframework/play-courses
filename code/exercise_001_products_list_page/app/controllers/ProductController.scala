package controllers

import play.api.i18n._
import javax.inject.Inject
import play.api.mvc.{Action, AbstractController, ControllerComponents, Request, AnyContent}
import models.Product

class ProductController @Inject() (cc: ControllerComponents) extends AbstractController(cc) with I18nSupport {
  def list = Action { implicit request: Request[AnyContent] =>
    val products = Product.findAll

    Ok(views.html.list(products))
  }
}
