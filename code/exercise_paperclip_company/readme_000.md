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

Try to build and run the project, normaly if everything is good, when you go to [http://localhost:9000](http://localhost:9000):

The Play application responds: `Welcome to the Hello World Tutorial!`
