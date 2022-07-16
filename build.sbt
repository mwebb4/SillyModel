ThisBuild / version := "0.1.0"
ThisBuild / scalaVersion := "2.12.12"
ThisBuild / organization := "com.example"

lazy val hello = (project in file("."))
  .settings(
    name := "Hello",
    libraryDependencies += "com.typesafe.play" %% "play-json" % "2.9.2",
    libraryDependencies += "com.lihaoyi" %% "requests" % "0.7.0",
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.7" % Test
  )
