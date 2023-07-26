## Background

This course is based on the example of the book `Play For Scala` written by `Peter Hilton, Erik Bakker and Francisco Canedo` in 2014. This example has been adapted to be based on Scala 3 and use modern technologies. The goal of this course is to create an application for an office furniture company.

## Objectives

During this course you are going to create a complete application with multiple functions :

* Display the products list
* Display the product details
* Generate a barcode for each product
* Create a new product
* Edit a product
* Deploy a database and connect it to the application

The focus of this course is more on: "How to get a simple, functional application rather than a pretty one" (so very little CSS).

## Steps

Currently, this course is divided in five exercises :

1. Understanting of the structure of Play Framework project
2. Create an home page with the products list
3. Create a details page for each product and generate a barcode for each one
4. Create functions to create and edit a product
5. Connect a database the the application

## Project structure

A Play application is based on the MVC pattern:

* Model: Manages the data and business logic
* View: Manages the layout and display
* Controller: Routes commands from the model and view parts

For now our application has not any model (we will come back to this later)

The `build.sbt` file is a configuration file for a Play application built using the Scala build tool (sbt). It contains information about the project's dependencies, settings, and build configuration.

The `plugins.sbt` file is used in a Scala project to specify the plugins that should be used by the build tool (sbt) when building the project. It is located in the `project` directory of a Play application and is used to declare sbt plugins and their versions.

The `routes` file is used to define the application's HTTP routes. It maps HTTP requests to controller methods, and it is responsible for directing requests to the appropriate controller action.

## Build and run the project

Currently, this project is just a simple application based on the Hello World exemple from Play Framework.

Try to build and run the project, normaly if everything is good, when you go to [http://localhost:9000](http://localhost:9000):

The Play application responds: `Welcome to the Hello World Tutorial!`
