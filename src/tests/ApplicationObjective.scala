package tests

import org.scalatest._
import pbd.PaleBlueDot

class ApplicationObjective extends FunSuite {

  val citiesFilename: String = "data/cities.csv"

  test("test 1"){

    assert(PaleBlueDot.whereToMeet("data/cities.csv", List("us","buffalo", "NY"), List("us","rochester","NY")) == List("us", "batavia", "NY"), 1)
    //assert(PaleBlueDot.whereToMeet("data/cities.csv", List("tr","karacabey","16"), List("sk","myjava","06")) == List("rs", "kladovo", "00"), 2)
    //assert(PaleBlueDot.whereToMeet("data/cities.csv", List("ro","salatrucel","39"), List("ru","russkaya polyana","54")) == List("ru", "bolshaya chernigovka", "65"), 3)

  }

}
