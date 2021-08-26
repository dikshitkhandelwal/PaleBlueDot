package pbd

import java.awt.Desktop
import java.net.URI
import scala.io.{BufferedSource, Source}
import scala.io.Source
object PaleBlueDot {


  /**
   * Lecture Objective 1
   *
   * Converts degrees into radians
   *
   * @param degrees A value provided in degrees
   * @return The radian equivalent of the input value
   */
  def degreesToRadians(degrees: Double): Double = {
      val radius: Double = degrees * Math.PI /180
      radius.toDouble
  }


  /**
   * Lecture Objective 2
   *
   * Given a country name using and case (upper/lower), return the country code in all lowercase letters
   *
   * Ex. If "Heard Island and McDonald Islands#HM" is a line countriesFilename
   * and countryName is "hEaRd IsLaNd AnD mCdOnAlD iSlAnDs" the returned value is "hm"
   *
   * If countryName is not in the file, return the empty String: ""
   *
   * @param countriesFilename Name of the file containing country names and codes
   * @param countryName       The name of the country to lookup in the file with any mix of upper/lower-case
   * @return The two letter country code for countryName
   */

  def getCountryCode(countriesFilename: String, countryName: String): String = {
    val countriesFile: BufferedSource = Source.fromFile(countriesFilename)
    var mainoutput_one : String = ""
    for (line <- countriesFile.getLines()){
      val splittedline : Array[String] = line.split("#")
      val countryname : String = splittedline(0)
      val lower: String =  countryname.toLowerCase()
      if (lower == countryName.toLowerCase()){
        mainoutput_one = splittedline(1)
      }
    }
    var mainoutput : String = mainoutput_one.toLowerCase()
    mainoutput
  }


  /**
   * Lecture Objective 3
   *
   * Returns a Map[cityName -> population] for all cities in the given county and region. The name of each
   * city should match exactly how it appears in citiesFilename and the population is read from the file
   * and converted to an Int.
   *
   * Ex: PaleBlueDot.cityPopulations(citiesFilename, "ad", "04") returns Map("la massana" -> 7211) since
   * "la massana" is the only city in region "04" of Andorra (code "ad") and its population is 7211
   *
   * @param citiesFilename Name of the file containing city name, population, and location data
   * @param countryCode    A two character country code
   * @param region         A two character region code
   * @return A Map containing the name and population of every city matching both the countryCode and region
   */
  def cityPopulations(citiesFilename: String, countryCode: String, region: String): Map[String, Int] = {
    val getfile: BufferedSource = Source.fromFile(citiesFilename)
    var output : Map[String, Int] = Map()
    for (line <- getfile.getLines()){
      var splitted :  Array[String] = line.split(",")
      if (splitted(0) == countryCode && splitted(2) == region ){
        output = output + (splitted(1) -> splitted(3).toInt)
      }
    }
    output
  }


  /**
   * Lecture Objective 4
   *
   * Returns a List of city names in the given county and region with a population at least minPopulation.
   *
   * @param citiesFilename Name of the file containing city name, population, and location data
   * @param countryCode    A two character country code
   * @param region         A two character region code
   * @param minPopulation  the minimum population that could be returned
   * @return All city names in countryCode/region with a population >= minPopulation
   */
  def bigCities(citiesFilename: String, countryCode: String, region: String, minPopulation: Int): List[String] = {
    val cityfile : BufferedSource = Source.fromFile(citiesFilename)
    var output : List[String] = List()
    for (line <- cityfile.getLines()){
      val splitted : Array[String] = line.split(",")
      val f: String = splitted(3)
      if (splitted(0) == countryCode && splitted(2) == region && f.toInt >= minPopulation){
        output = output :+ splitted(1)
      }
    }
    output.sorted
  }


  /**
   * Lecture Objective 5
   *
   * Computes the grater circle distance ("As the crow flies") between two locations on Earth in kilometers.
   * The input locations are given as Lists of Double containing the latitude and longitude coordinates of that
   * location. For example, if the location is latitude: 35.685 and longitude: 139.751389 the input would be
   * List(35.685, 139.751389).
   *
   * @param location1 A location on Earth given as a List containing latitude and longitude coordinates
   * @param location2 A location on Earth given as a List containing latitude and longitude coordinates
   * @return The greater circle distance between the two input locations
   */
  def greaterCircleDistance(location1: List[Double], location2: List[Double]): Double = {

    val lat1: Double = location1(0)
    val lon1: Double = location1(1)

    val lat2 : Double = location2(0)
    val lon2 : Double = location2(1)


    val R:Double = 6371.0
    val y:Double = lat1 * Math.PI/180
    val z:Double = lat2 * Math.PI/180
    val h:Double = (lat2-lat1) * Math.PI/180
    val j:Double = (lon2-lon1) * Math.PI/180

    val a = Math.sin(h/2) * Math.sin(h/2) + Math.cos(y) * Math.cos(z) * Math.sin(j/2) * Math.sin(j/2)
    val c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a))

    val d = R * c;
    d

  }


  /**
   * Programming Objective 1jr
   *
   * You find yourself stranded in an unfamiliar place with no signs of civilization. You don't have much with you,
   * but you do have a locator that gives your current latitude/longitude, a csv file of cities, and your final
   * submission to the PaleBlueDot assignment from CSE116 (What luck!). You decide that finding and walking
   * directly to the closest city will give you the best chance to survive.
   *
   * @param citiesFilename Name of the file containing city name, population, and location data
   * @param location       A location on Earth given as a List containing latitude and longitude coordinates
   * @return The city closest to the given location as a List containing country code, city name, and region
   *         exactly as they appear in the cities file
   */
   def closestCity(citiesFilename: String, location: List[Double]): List[String] = {
     val file: BufferedSource = Source.fromFile(citiesFilename)
     var coun : String = ""
     var city: String = ""
     var region: String = ""
     var dist = 1000000000000000000000000.0
    for (line <- file.getLines().drop(1)){
      val spl: Array[String] = line.split(",")
      val g = greaterCircleDistance(List(spl(4).toDouble, spl(5).toDouble), location )
      if (dist > g){
        coun = spl(0)
        city = spl(1)
        region = spl(2)
        dist = g
      }

    }
     List(coun, city, region)

  }

  /**
   * Programming Objective 2
   *
   * Find the population of a country by name. Not quite a life or death situation, but interesting information
   * regardless.
   *
   * @param countriesFilename Name of the file containing country names and codes
   * @param citiesFilename    Name of the file containing city name, population, and location data
   * @param countryName       The name of the country with any mix of upper/lower-case
   * @return The total population of the given country
   */
  def countryPopulation(countriesFilename: String, citiesFilename: String, countryName: String): Int = {
    val countryinit: String = getCountryCode(countriesFilename, countryName)
    val file: BufferedSource = Source.fromFile(citiesFilename)
    var output: Int = 0
    for (line <- file.getLines()){
      val split: Array[String] = line.split(",")
      if(split(0) == countryinit){
        output += split(3).toInt
      }
    }
    output
  }


  /**
   * Application Objective
   *
   * You're in a city. I'm in a city. We want to meet in a city with a fair split of travel distance for each of us.
   * We happen to both own helicopters so we'll travel "as the crow flies" and we're not concerned about roads or
   * oceans. We just need to find the city closest to the midpoint between our two cities and we'll meet there.
   *
   * Each city is provided to this method as a List containing the country code, name, and region exactly as they
   * appear in the cities file (ie. Don't do anything with upper/lower-case in this method.) The returned city should
   * follow the same formatting (Don't modify the upper/lower-case of any Strings).
   *
   * @param citiesFilename Name of the file containing city name, population, and location data
   * @param city1          A city as a List containing country code, name, and region exactly as they appear in the
   *                       cities file
   * @param city2          A city as a List containing country code, name, and region exactly as they appear in the
   *                       cities file
   * @return The city closest to the midpoint of the two input cities as a List containing country code, city name,
   *         and region exactly as they appear in the cities file
   *
   */

def getlat(citiesFilename: String, city: List[String]): List[Double] = {
  val file: BufferedSource = Source.fromFile(citiesFilename)
  var lat = 0.0
  var lon = 0.0
  for (line <- file.getLines().drop(1)) {
    var spl: Array[String] = line.split(",")
    if (city(0) == spl(0) && city(1) == spl(1) && city(2) == spl(2)) {
      lat = spl(4).toDouble
      lon = spl(5).toDouble
    }
  }
  List(lat, lon)
}

  def whereToMeet(citiesFilename: String, city1: List[String], city2: List[String]): List[String] = {

    val h = getlat(citiesFilename, city1)
    val j = getlat(citiesFilename, city2)

    val a = degreesToRadians(h.head) //latitude 1
    val b = degreesToRadians(j.head)  //latitude 2

    println(a)
    println(b)

    val p = degreesToRadians(h(1))//longitude 1
    val q = degreesToRadians(j(1)) // longitude 2

    println(p)
    println(q)

    val Bx = Math.cos(b) * Math.cos(q-p)
    val By = Math.cos(b) * Math.sin(q-p)
    val lat = Math.atan2(Math.sin(a) + Math.sin(b), Math.sqrt( (Math.cos(a)+Bx)*(Math.cos(a)+Bx) + By*By ) )
    val lon = p + Math.atan2(By, Math.cos(a) + Bx)
    println(lat)
    println(lon)

    val nal = closestCity(citiesFilename, List(lat*180/Math.PI, lon*180/Math.PI))
    nal
  }


  /**
   * Helper Method
   *
   * Opens Google Maps at a specific location. The location is a List containing the latitude then longitude as Doubles
   *
   * @param location The location to open in the format List(Latitude, Longitude)
   */
  def openMap(location: List[Double]): Unit = {
    if (Desktop.isDesktopSupported && Desktop.getDesktop.isSupported(Desktop.Action.BROWSE)) {
      val url: String = "http://maps.google.com/maps?t=m&q=loc:" + location.head.toString + "+" + location(1).toString
      Desktop.getDesktop.browse(new URI(url))
    } else {
      println("Opening the browser not supported")
    }
  }


  def main(args: Array[String]): Unit = {
    println(whereToMeet("data/cities.csv", List("us","sartell","MN"), List("us","saint peter","MN")))
  }




}


