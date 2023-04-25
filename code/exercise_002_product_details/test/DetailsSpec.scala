import org.scalatestplus.play.*
import models.Product

class DetailsSpec extends PlaySpec {
  "Product#findByEan".must({
    "return a product".in({
      val product = Some(Product(5010255079763L, "Paperclips Large", "Large Plain Pack of 1000"))

      val actualProducts: Option[Product] = Product.findByEan(5010255079763L)

      assert(product == actualProducts)
    })
  })
}
