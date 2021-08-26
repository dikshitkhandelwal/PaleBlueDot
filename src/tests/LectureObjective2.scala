package tests

import org.scalatest._
import sun.tools.jconsole.InternalDialog
import pbd.PaleBlueDot

class LectureObjective2 extends FunSuite {

  val countriesFile: String = "data/countries.txt"

  test("Testing Objective 2") {
      val first : String = "India"
     val second : String = "Israel"
    val third : String = "InDiA"


    assert(PaleBlueDot.getCountryCode(countriesFile, first) == "in")
    assert(PaleBlueDot.getCountryCode(countriesFile, second) == "il")
    assert(PaleBlueDot.getCountryCode(countriesFile, third) == "in")

  }

}
