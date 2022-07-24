ThisBuild / version := "0.1.0"
ThisBuild / scalaVersion := "2.12.12"
ThisBuild / organization := "com.example"

lazy val SillyDemoModel = (project in file("."))
  .enablePlugins(JavaAppPackaging)
  .settings(
    name := "SillyDemoModel",
    libraryDependencies += "org.rogach" %% "scallop" % "4.1.0", // Args Parsing and Config
    libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.2.10", // slf4j Java logging backend
    libraryDependencies += "com.typesafe.scala-logging" %% "scala-logging" % "3.9.4", // logging classes
    libraryDependencies += "org.apache.spark" % "spark-core_2.12" % "3.3.0" % "provided",
    libraryDependencies += "org.apache.spark" % "spark-mllib_2.12" % "3.3.0" % "provided",
    libraryDependencies += "org.apache.spark" %% "spark-sql" % "3.3.0" % "provided",
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.7" % Test
  )

assemblyJarName in assembly := "silly-model-fatjar-0.1.0.jar"
