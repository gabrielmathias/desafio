name := "desafio"

version := "1.0"

organization := "br.com"

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % "2.0.0" ,
  "org.apache.spark" %% "spark-sql"  % "2.0.0",
  "org.slf4j" % "slf4j-api" % "1.7.5",
  "org.slf4j" % "slf4j-simple" % "1.7.5"
//  "org.apache.spark" %% "spark-hive" % "2.0.0"
)

scalaVersion := "2.11.7"
