lazy val scala3 = "3.3.0-RC5"

lazy val root = (project in file("."))
  .enablePlugins(PlayScala)
  .settings(
    name := """play-scala-hello-world-tutorial""",
    organization := "com.example",
    version := "1.0-SNAPSHOT",
    crossScalaVersions := Seq("2.13.10", scala3),
    scalaVersion := scala3,
    libraryDependencies ++= Seq(
      guice,
      jdbc,
      "org.postgresql" % "postgresql" % "42.6.0",
      "org.scalatestplus.play" %% "scalatestplus-play" % "6.0.0-M3" % Test,
      "net.sf.barcode4j" % "barcode4j" % "2.1"
    ),
    scalacOptions ++= Seq(
      "-feature",
      "-Werror"
    )
  )
