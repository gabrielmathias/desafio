package desafio

import main.scala.desafio.SparkBirthdays
import org.scalatest.{FeatureSpec, GivenWhenThen}

/**
  * Created by gabriel mathias on 1/30/17.
  */
class SparkBirthdays$Test extends FeatureSpec with GivenWhenThen {

  feature("Calling main method when there are no application.config file"){
    scenario("Not passing any input CSV file nor output path"){
      assertThrows[Exception] {
        SparkBirthdays.main(Array[String]())
      }
    }
    scenario("Not passing any input CSV file but passing output path"){
      assertThrows[Exception] {
        SparkBirthdays.main(Array("Out.json"))
      }

    }
    scenario("Passing an MISSING input CSV and output path"){
      assertThrows[Exception] {
        SparkBirthdays.main(Array("data/NonExisting.csv", "Out.json"))
      }
    }
    scenario("Passing both input CSV and output path"){
      SparkBirthdays.main(Array("data/Mock.csv","Out.json"))
    }
  }
}
