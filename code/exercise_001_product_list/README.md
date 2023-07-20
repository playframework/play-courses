# Creating a product list page

## Introduction

In this exercise, we will add a product page to our Play application. To do this, we need to complete the following steps:

- Adding a Product model
- Create the Product list page
- Custom Stylesheet
- Create the controller action method
- Adding a route configuration
- Delete the welcome page & redirect our user to our new page

## Steps

### Adding a Product model

First thing first, we need to create a model that represents the date we are going to work with.
Ours is simple, we have a product with an EAN code, a name, and a description.

Models are usually stored in: `app/models`.

| Hint: The idiomatic of doing that in Scala & Play is to use a case clase |
| ------------------------------------------------------------------------ |

We can create a Data Access Object (DAO) to provide access to our product data. As we didn't implement persistent storage for now, we need to hardcode a few products for testing. Here is a list of example products you can use:

```scala
val products = Set(
  Product(5010255079763L, "Paperclips Large",
    "Large Plain Pack of 1000"),
  Product(5018206244666L, "Giant Paperclips",
    "Giant Plain 51mm 100 pack"),
  Product(5018306332812L, "Paperclip Giant Plain",
    "Giant Plain Pack of 10000"),
  Product(5018306312913L, "No Tear Paper Clip",
    "No Tear Extra Large Pack of 1000"),
  Product(5018206244611L, "Zebra Paperclips",
    "Zebra Length 28mm Assorted 150 Pack")
)
```

Go ahead and create a function that returns a list of all the products sorted by their EAN, you can name this function `findAll`.

### Create the Product list page

We now want to display the product we created earlier, for that, we need to create a page that will hold our content.

The view files are usually located in: `app/views`.

A main template is already provided for us, we can make use of it by inserting our view into it.
For that, you will need to add to the template file a reference to the list of products and call the main template.

Play uses Twirl as its templating engine, which means that we can integrate Scala code into our HTML. We can make use of that to display our list of products.

### Custom Stylesheet

Stylesheets are located in: `public/stylesheets`. Create a new file and add the following code:

```css
.products {
  padding: 1rem;
}

.product {
  border: 1px black solid;
}

.product dt {
  font-weight: bold;
  font-size: 1.5rem;
}

.products dt {
  padding: 1rem;
}

.product dd {
  padding: 1rem;
}

footer {
  padding: 1rem;
  text-align: center;
}

footer p {
  font-weight: bold;
  color: black;
}
```

To include it in our application add the following line in `main.scala.html`:

```scala
<link rel="stylesheet" media="screen" href='@routes.Assets.versioned("stylesheets/stylesheet-name.css")'>
```

### Create the controller action method

Now that we have a model and a view template, we need code that will connect the two together. This is the role of a controller.
It's responsible for handling HTTP requests and generating responses. You can have a look at the `HomeController.scala` for inspiration.

Controller files are located in: `app/controllers`.

You will need to create a `list` function that returns an `Action`. You will also need to import the product model to use the `findAll` function you made earlier.

### Adding a route configuration

To access the product list, we need to create a route that points to our controller action.

The route configuration file is located in: `conf/routes`.

You need to specify the HTTP action, the path, and the controller action.

### Delete the welcome page & redirect our user to our new page

We don't need the welcome page anymore, you can delete the related files and redirect our users to the product page directly.
