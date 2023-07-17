# Creating a product detail page

## Tasks

In this exercise, we will add a product details page to our Play application. To do this, we need to complete the following steps:

- Create a new finder method to fetch one specific product
- Create a view template to show the product details page
- Adding a route configuration, the URL should have a parameter
- Generate a barcode image to display on the page

## Steps

### The model finder method

Add a new method to the Product.scala model, we will name this one `findByEan`. The objective of this new method is to find a specific product based on its EAN.

| Hint: Have a look at the `find()` method |
| ------------------------------------------ |

### Messages

In the message files you should add new messages:

**conf/messages**

```
ean = EAN
name = Name
description = Description
product.details = Product: {0}
```

**conf/messages.es**

```
ean = EAN
name = Nombre
description = Descripción
product.details = Producot: {0}
```

**conf/messages.fr**

```
ean = EAN
name = Nom
description = Descriptif
product.details = Produit: {0}
```

**conf/messages.nl**

```
ean = EAN
name = Naam
description = Omschrijving
product.details = Product: {0}
```

### Templates

You should create two new templates, the first one will be used to show the details of a specific product, and the second one the bar code. Indeed, later we plan to reuse the latter. The new templates should be named:

- `details.scala.html`
- `barcode.scala.html`

#### Details Template

In this template, we want:

- The product barcode
- The product EAN
- The product name
- The product description

In the folder `view` create a new folder named `products` and in this folder create the template.

The template structure is:

```html
@(product: Product)(implicit messages: MessagesProvider)
@main(Messages("product.details", product.name)) {
    <div class="title-container">
        <h2>
            @Messages("product.details", product.name)
        </h2>
        @tags.barcode(product.ean)
    </div>

    <dl class="details">
        <dt>[...]:</dt>
        <dd>@product.ean</dd>

        <dt>@Messages("name"):</dt>
        <dd>[...]</dd>

        <dt>[...]:</dt>
        <dd>[...]</dd>
    </dl>

```

You should replace the [...] with the good data.

In the stylesheet, create a new css file and add this code:

```css
.title-container {
    overflow: auto;
    margin-top: 1em;
}

.title-container h2 {
    margin-left: 1em;
    float: left;
}

.title-container img {
    float: right;
}

.details {
    margin-top: -3em;
    margin-left: 2em;
}
.details dt {
    float: left;
    clear: left;
    width: 10em;
    font-weight: bold;
    color: green;
}

.details dd {
    margin: 0 0 0 2em;
    padding: 0 0 0.5em 0;
}
```

In main.scala.html add :

```html
<link rel="stylesheet" media="screen" href='@routes.Assets.versioned("stylesheets/details.css")'>
```

#### Barcode Template

In `view` create a new folder named `tags` and in this one create the template `barcode.scala.html`

The barcode template is really short, we just want an image with the barcode of the target product:

```html
@(ean: Long)
<img class="barcode" src="[...]" alt="@ean">
```

As with the previous template, you should replace the [...] with the good data. However, we are going to see the routing and the controller creation in the next steps (you will need it to complete the template)

## Adding a new action to the `ProductController` action

In this part, we will create a new action named `show()` in the `ProductController`. We will pass to this one a parameter whose value will be the requested product’s EAN code. For this action, we should use our new method findByEan to return the `details.scala.html` with the product wanted. If no product was found we should return a 404 error.

## Create a new route for `show` with a parameter

The next step is to create the associated route to our new `show` action.

| Hint: You should think to add the parameter in the URL |
| ------------------------------------------------------ |

## Add the barcode4j library

We should add in build.sbt the library to generate the barcode:

```
"net.sf.barcode4j" % "barcode4j" % "2.1"
```

## Create the `BarcodeController`

For now, you can just take the code below and create the template named `BarcodeController.scala`

```java
package controllers

import javax.inject.Inject
import play.api.mvc.{Action, AbstractController, ControllerComponents}

class BarcodeController @Inject() (cc: ControllerComponents) extends AbstractController(cc) {
    val ImageResolution = 144
    def barcode(ean: Long) = Action {
        import java.lang.IllegalArgumentException
        val MimeType = "image/png"
        try {
            val imageData = ean13BarCode(ean, MimeType)
            Ok(imageData).as(MimeType)
        }
        catch {
            case e: IllegalArgumentException =>
                BadRequest("Couldn’t generate bar code. Error: " + e.getMessage)
        }
    }
    def ean13BarCode(ean: Long, mimeType: String): Array[Byte] = {
        import java.io.ByteArrayOutputStream
        import java.awt.image.BufferedImage
        import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider
        import org.krysalis.barcode4j.impl.upcean.EAN13Bean
        val output: ByteArrayOutputStream = new ByteArrayOutputStream
        val canvas: BitmapCanvasProvider =
            new BitmapCanvasProvider(output, mimeType, ImageResolution,
                BufferedImage.TYPE_BYTE_BINARY, false, 0)
        val barcode = new EAN13Bean()
        barcode.generateBarcode(canvas, String valueOf ean)
        canvas.finish

        output.toByteArray
    }
}
```

Now if you try to go on this route : ``` http://localhost:9000/products/5010255079763 ``` you should be able to see the paperclip large details.