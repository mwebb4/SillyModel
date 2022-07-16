ThisBuild / version := "0.1.0"
ThisBuild / scalaVersion := "2.12.12"
ThisBuild / organization := "com.example"

//val projectMainClass = "com.saeed.ApplicationMain"

//mainClass in (Compile, run) := Some(projectMainClass)

lazy val demoModel = (project in file("."))
  .enablePlugins(JavaAppPackaging)
  .settings(
    name := "SillyDemoModel",
    libraryDependencies += "com.typesafe.play" %% "play-json" % "2.9.2",
    libraryDependencies += "com.lihaoyi" %% "requests" % "0.7.0",
    libraryDependencies += "org.apache.spark" % "spark-core_2.12" % "3.3.0",
    libraryDependencies += "org.apache.spark" % "spark-mllib_2.12" % "3.3.0",
    libraryDependencies += "org.apache.spark" %% "spark-sql" % "3.3.0",
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.7" % Test
  )
