# SparkBirthdays
Spark application for a job interview
The setup used is described in SETUP.md file

##HOW TO RUN

First generate the jar package file of the project:

```
$> sbt package
```

Then you can run manually:

Make sure you have all the paths bellow adjusted, then run:

```
$> spark-submit --class main.scala.desafio.SparkBirthdays /home/gabriel/ideia/desafio-programador/target/scala-2.11/desafio_2.11-1.0.jar /home/gabriel/Downloads/Mock.csv /home/gabriel/Downloads/Out.json
```

Or you can use the IntelliJ IDEA to run the code:

First create an "Run Configuration", use the menu "Run"-> "Edit Configurations" click on the "+(plus)" sign, then use the image: data/run-configuration.png as axample and save the configuration.
Then, with the file "SparkBirthdays" opened in the text editor, use the menu "Run"->"Run 'SparkBirthdays".


Or you can do it by running the script provided (only in Linux and maybe MacOS):
Just run:
```
$> sh run.sh
```

##RUNNING THE TESTS

Via SBT

Simply call:

```
$> sbt test
```

Or execute :
```
$> sbt
```

Then when SBT prompt is ready, call:
```
> test
```
