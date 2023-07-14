# Adding a new product

## Tasks

In this exercise, we'll create a form to add a new product to our catalog.
We can break it down into the following tasks:

- Create a form object
- Create a function to save our products
- Display the form to our users
- Create a route for saving products
- Add validation to the user input

## Steps

### Create a form object

In the `ProductController`, create a value called `productForm` of type `Form[Product]`. It will hold the mapping from the user input to our Product model.

### Add validation to the user input

We can add validation rules to our product form right away. We don't want duplicated EAN and empty name & description.

### Create a function to save our products

In the `ProductController`, we can now add a function to create a new product. We have two possible outcomes, either the form is invalid or valid.
In case of an invalid form state, we want to send back a `BadRequest` containing the form's errors.
In case of a valid form state, we can add the new product to our list and redirect the user to our page with a successful flash message.

### Create a route for saving products

Now that we have a `create` function, we can create the associated route. Update `conf/routes` with the correct HTTP method and the corresponding controller function.

### Display the form to our users

For simplicity, we can add the form in the same place as our product list.
To create a form, you can make use of the `@helper` package provided by Play. The syntax is the following:

```scala
@helper.form(/* routes to the create product function */) {}
```

Inside this, you can then add the input field you need. The helper will take care of displaying information (required input) & validation errors.

And we are done with this part! Our users can now create a product and see it instantly in the product list we made earlier.

## References

(Handling form submission)[https://www.playframework.com/documentation/2.8.x/ScalaForms]
