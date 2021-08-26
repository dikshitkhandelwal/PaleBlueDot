package tests

import org.scalatest._
import pbd.PaleBlueDot

class ProgrammingObjective2 extends FunSuite {

  val countriesFile: String = "data/countries.txt"
  val citiesFilename: String = "data/cities.csv"

  test("test 1"){

    val confile: String = "data/countries.txt"
    val cityfile: String = "data/cities.csv"
    assert(PaleBlueDot.countryPopulation(confile, cityfile, "India") == 259227307, "India")
    assert(PaleBlueDot.countryPopulation(confile, cityfile, "Libya") == 3861660, "Libya")
  }

}
