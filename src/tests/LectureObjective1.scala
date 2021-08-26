package tests

import org.scalatest._
import pbd.PaleBlueDot

class LectureObjective1 extends FunSuite {

  val EPSILON: Double = 0.001

  def compareDoubles(d1: Double, d2: Double): Boolean = {
    Math.abs(d1 - d2) < EPSILON
  }


  test("test 1") {

    val ninety: Double = 90.0
    val fourtyfive :Double = 45.0
    val oneeighty: Double = 180.0

    assert(compareDoubles(PaleBlueDot.degreesToRadians(ninety), 1.56999), ninety)
    assert(compareDoubles(PaleBlueDot.degreesToRadians(oneeighty), 3.14160), ninety)
    assert(compareDoubles(PaleBlueDot.degreesToRadians(fourtyfive), 0.78588), ninety)

  }


}
