package controllers

import javax.inject.Inject
import play.api.mvc.{Action, AbstractController, ControllerComponents, Request, AnyContent}
import models.Product

class ProductController @Inject() (cc: ControllerComponents) extends AbstractController(cc) {
  def list = Action { implicit request: Request[AnyContent] =>
    val products = Product.findAll

    Ok(views.html.list(products))
  }
}
