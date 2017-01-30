#!/bin/bash

spark-submit --class main.scala.desafio.SparkBirthdays target/scala-2.11/desafio_2.11-1.0.jar data/Mock.csv Out.json

echo "Results in Out.json folder!" 

