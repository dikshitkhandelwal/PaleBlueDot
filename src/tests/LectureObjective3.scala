package tests

import org.scalatest._
import pbd.PaleBlueDot

class LectureObjective3 extends FunSuite {

  val citiesFilename: String = "data/cities.csv"

  test("test 1") {

      val one = Map("la massana" -> 7211)
      val second = Map("dubai" -> 1137376)
        val third = Map("bajram curri" -> 7967, "fierze" -> 742, "kukes" -> 17972)

    assert(PaleBlueDot.cityPopulations(citiesFilename, "ad", "04") == one, one)
    assert(PaleBlueDot.cityPopulations(citiesFilename, "ae", "03") == second, second)
    assert(PaleBlueDot.cityPopulations(citiesFilename, "al", "47") == third, third)

  }

}
