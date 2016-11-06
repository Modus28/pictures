package dbg28.Dirty

import dbg28.InputVerification

import scala.util.matching.Regex


/**
  * EECS 293
  * Created by Daniel on 11/6/2016.
  * dbg28@case.edu
  *
  * Accepts user input in List format from InputHandler, converts it into Graphs and Points\
  * On the untrusted side of the barricade
  */
object DataPackaging {
  val graphPattern: Regex = "/([A-Z*])/g".r
  val rowOrColumnPattern: Regex = "(\\d)".r




  def processInput(input: List[String]): Unit = {
    // Parse out the first two lines as integers and set those as locally stored variables
    val dimensions = parseDimensions(input)
    InputVerification.setDimensions(dimensions.head, dimensions(1))

    // For each set of text between empty lines, create a Set of Points for it,
    // send all but the last one of the list of these sets to a Pictures process method
    // Send the final set, the completed graph, to a separate processing method
  }

  /**
    * Parses the List form of the user input to identify
    * @param toCheck the UserInput list to find dimensions in
    * @return the Strings containing the graph dimensions
    */
  def parseDimensions(toCheck: List[String]): List[String] = {
    val rows = graphPattern.findFirstMatchIn(toCheck.head)
    val columns = graphPattern.findFirstMatchIn(toCheck(1))
    List(rows.get.group(0), columns.get.group(0))
  }
}

/** Stores a Set of Points and an upper case character that the points contain */
case class Graph(points: Set[Point], char: Character)
/** Point coordinates in a 2D plane */
case class Point(x: Int, y: Int)