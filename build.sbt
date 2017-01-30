name := "desafio"

version := "1.0"

organization := "br.com"

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % "2.0.0" ,
  "org.apache.spark" %% "spark-sql"  % "2.0.0"
//  "org.slf4j" % "slf4j-api" % "1.7.5",
//  "org.slf4j" % "slf4j-simple" % "1.7.5"
)

libraryDependencies += "org.scalactic" %% "scalactic" % "3.0.1"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.1" % "test"

scalaVersion := "2.11.7"
