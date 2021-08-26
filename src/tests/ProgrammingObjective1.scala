package tests

import org.scalatest._
import pbd.PaleBlueDot

class ProgrammingObjective1 extends FunSuite {

  val citiesFilename: String = "data/cities.csv"

  test("test 1") {

    assert(PaleBlueDot.closestCity(citiesFilename, List(35.03, 71.0)) == List("af", "asadabad", "34"), "Test 1")
    assert(PaleBlueDot.closestCity(citiesFilename, List(42.222,20.0556)) == List("al", "fierze", "47"), "Test 2")
    assert(PaleBlueDot.closestCity(citiesFilename, List(39.79,45.36)) == List("am", "vernashen", "10"), "Test 3")

  }

}
