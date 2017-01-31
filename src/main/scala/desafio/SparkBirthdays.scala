
package main.scala.desafio

import org.apache.spark.{SparkConf, sql}
import org.apache.spark.sql.{SaveMode, SparkSession}
import org.apache.spark.sql.types.StructType
import org.slf4j.LoggerFactory
import com.typesafe.config._



/**
  * Running:
  *
  * $> spark-submit --class main.scala.desafio.SparkBirthdays target/scala-2.11/desafio_2.11-1.0.jar <path_to_csv_input_file> <path_to_json_output_file>
  *
  */
class SparkBirthdays {

}

/**
  * Created by gabriel mathias
  */
object SparkBirthdays {

  def main(args: Array[String]): Unit = {
    val logger = LoggerFactory.getLogger(classOf[SparkBirthdays])

    def getParams = {
      logger.debug("Searching for execution parameters")

      if (args.length < 2) {
        val conf = ConfigFactory.load().getConfig("desafio")
        if (conf.entrySet().size() < 2) {
          logger.warn("Failed to find valid parameters")
          logger.debug("Execution of SparkBirthdays failed due missing parameters and/or configuration file")
          println("Usage: spark-submit --class main.scala.desafio.SparkBirthdays target/scala-2.11/desafio_2.11-1.0.jar <path_to_csv_input_file> <path_to_json_output_file")
          println("Or provide a application.conf on the classpath with the folowing variables: desafio.input, desafio.output")
          throw new Exception("Execution of SparkBirthdays failed due missing parameters")
        } else {
          logger.debug("Parameters sent by configuration file")
          val desafioInput: String = conf.getString("input")
          val desafioOutput: String = conf.getString("output")
          (desafioInput,desafioOutput)
        }
      } else {
        logger.debug("Parameters sent by arguments in the command line")
        val csvInput = args(args.length - 2)
        val jsonOutput = args(args.length - 1)
        (csvInput, jsonOutput)
      }
    }

    /* Loading execution parameters */
    val (inputCSV, outputCSV) = getParams

    def getSparkSession: SparkSession = {
      val conf = new SparkConf()
        .setMaster("local[2]")
        .setAppName("SparkBirthdays")

      logger.debug("Initializing SparkSession")

      SparkSession
        .builder()
        .config(conf)
        .getOrCreate()
    }

    def getInputDataFrame(sparkSession: SparkSession): sql.DataFrame = {
      val schema = new StructType()
        .add("name", "string")
        .add("email", "string")
        .add("gender", "string")
        .add("birthday", "string")

      logger.debug("Retrieving the input DataFrame")
      sparkSession
        .read
        .schema(schema)
        .option("header", "true")
        .csv(inputCSV)
    }


    logger.info("Preparing to process $inputCSV")

    logger.info("Obtaining the input DataFrame")
    val csvDF = getInputDataFrame(getSparkSession)

    /* This regex will filter strings representing dates where the month part is in {1,01,2,02,3,03,4,04,5,05,6,06} and year part is 4 or 2 digits long. */
    val firstSemesterMatching = "\\d{2}?\\d{2}-[0]?[1-6]-\\d{1,2}"

    logger.info("Applying filtering to the DataFrame")
    val firstSemesterDF = csvDF.filter(csvDF("birthday") rlike firstSemesterMatching)

    logger.info(s"Preparing to output JSON into $outputCSV")
    logger.warn(s"The file $outputCSV will be unmercifully overwritten!")

    firstSemesterDF
      .coalesce(1) // this will coalesce all the results into one single list inside one worker
      .write
      .mode(SaveMode.Overwrite) // this will overwrite the output file, if it exists
      .json(outputCSV) // creating the json strutured files on the jsonOutput path

    logger.info("Processing finished.")
  }
}