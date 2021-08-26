package tests

import org.scalatest._
import pbd.PaleBlueDot

class LectureObjective5 extends FunSuite {


  def Doubles(d1: Double, d2: Double): Boolean ={
    val eclipse: Double = 0.001
    Math.abs(d1 - d2) < eclipse
  }


  test("test 1") {

    assert(Doubles(PaleBlueDot.greaterCircleDistance(List(42.55,1.5166667), List(42.5,1.5333333)), 5.7250), 5.72505074632655)
    assert(Doubles(PaleBlueDot.greaterCircleDistance(List(31.820894,64.57), List(34.348167,62.2)), 357.3608), 357.36087414040173)
    assert(Doubles(PaleBlueDot.greaterCircleDistance(List(34.426468,70.45153), List(36.864768,70.834207)), 273.3219), 273.3219397156878)

  }

}
