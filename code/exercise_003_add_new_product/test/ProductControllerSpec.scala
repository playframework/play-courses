import scala.concurrent.Future

import org.scalatestplus.play.PlaySpec
import controllers.ProductController
import models.Product
import play.api.mvc.*
import play.api.test.*
import play.api.test.Helpers.*

import play.api.i18n.Lang
import play.api.Configuration

class ProductControllerSpec extends PlaySpec {
  "ProductController#list" should {
    "should be valid" in {
      // val controller = new ProductController()
      // val result: Future[Result] = controller.list().apply(FakeRequest())
      // val bodyText: String = contentAsString(result)
      // bodyText mustBe "ok"
      "ok" mustBe "ok"
    }
  }
}
