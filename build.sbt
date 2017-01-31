name := "desafio"

version := "1.0"

organization := "br.com"

libraryDependencies ++= Seq(
  "com.typesafe" % "config" % "1.3.1",
  "org.apache.spark" %% "spark-core" % "2.0.0" ,
  "org.apache.spark" %% "spark-sql"  % "2.0.0",
  "org.scalactic" %% "scalactic" % "3.0.1",
  "org.scalatest" %% "scalatest" % "3.0.1" % "test"

)

scalaVersion := "2.11.7"
