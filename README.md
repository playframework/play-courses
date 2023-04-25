# play-courses

This course is based on the example of the book `Play For Scala` written by `Peter Hilton, Erik Bakker and Francisco Canedo`.

The CMT complete documentation is available [here](http://cmt.lunatech.com/docs/getting_started).

## Requirement

To follow the steps in this tutorial, you will need the correct version of Java and sbt. The tutorial requires:

* Java Software Developer's Kit (SE) 11 or higher
* sbt 1.8.2 or higher. Note: if you downloaded this project as a zip file from https://developer.lightbend.com, the file includes an sbt distribution for your convenience.

To check your Java version, enter the following in a command window:

```bash
java -version
```

To check your sbt version, enter the following in a command window:

```bash
sbt sbtVersion
```

If you do not have the required versions, follow these links to obtain them:

* [Java SE](http://www.oracle.com/technetwork/java/javase/downloads/index.html)
* [sbt](http://www.scala-sbt.org/download.html)

## CMT installation

- Download the [latest CMT release](https://github.com/lunatech-labs/course-management-tools/releases)
- Unzip the zip file downloaded
- Update your PATH to include the `course-management-tools/bin` folder in
  the folder in which you unzipped the downloaded zip file

The installation documentation about the CMT is available [here](http://cmt.lunatech.com/docs/install).

To check if the installation is a success try to run:

```
~ cmta
~ cmtc
```

If the possible parameters are returned the installation is successful.

## CMT course setup

- Clone the repository to a directory of your choice
- At the root of the repository run the studentify: `cmta studentify -f -m . -d [target_dir_path]`
- Set up the studentified course as the current course: `cmtc set-current-course -s [target_dir_path]`

Once this is done you can run `cmtc` to display the possible commands

## Build and run the project

To build and run the project:

1. Use a command window to change into the code folder of the project directory, for example: `cd play-course/code`
2. Build the project. Enter: `sbt run`. The project builds and starts the embedded HTTP server. Since this downloads libraries and dependencies, the amount of time required depends partly on your connection's speed.
3. After the message `Server started, ...` displays, enter the following URL in a browser: [http://localhost:9000](http://localhost:9000)

## Release

- Push your changes & create a pull request
- CI will run tests on each exercise
- Merge the pull request into main
- Create a tag for the release (e.g., `2.9.0-M4`)
- Push the tag on the upstream repository (e.g. `git push --tags upstream`)
- CI will run tests & create a release -> A release consists of a student & a linearised repository

## Rules & Convention

Commits name:
Name the commit after the exercise name (e.g. exercise_00X_subject) when creating a new one.
When doing a fix, add the exercise(s) name and the change in the title (e.g. exercise_00X fix css main template).
When updating multiple exercises, the commit title should contain all the exercises' names and the subject of the change -> (e.g. exercises 001, 002, 00X => update http port application.conf).

It's always a good idea to add a description under the commit title.
- What are the changes
- Why did it change
- Meaningful resources related to the change
