package tests

import org.scalatest._
import pbd.PaleBlueDot

class LectureObjective4 extends FunSuite {

  val citiesFilename: String = "data/cities.csv"

  test("test 1") {

    val second: List[String] = List("andarab", "baglan", "nahrin")
    val three: List[String] = List("buffalo", "new york", "rochester", "syracuse", "yonkers")


    assert(PaleBlueDot.bigCities(citiesFilename, "af", "03", 5000).sorted == second.sorted, second)
    assert(PaleBlueDot.bigCities(citiesFilename, "us", "NY", 100000).sorted == three.sorted, three)

  }
}
