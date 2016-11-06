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
    val rows  = dimensions.head
    val columns = dimensions(1)
    InputVerification.setDimensions(rows, columns)

    // The user input without the rows and columns and empty lines
    val inputWithoutDimensions = input diff List(input.head, input(1),"")
    val rowInt: Int = rows.toInt + 1
    val graphStrings: List[List[String]] = inputWithoutDimensions.sliding(rowInt, rowInt-1).toList // check toInt failures
    println(graphStrings)

    // send all but the last one of the list of these sets to a Pictures process method
    // Send the final set, the completed graph, to a separate processing method
  }

  /**
    * Parses the List form of the user input to identify
    * @param toCheck the UserInput list to find dimensions in
    * @return the Strings containing the graph dimensions
    */
  private def parseDimensions(toCheck: List[String]): List[String] = {
    val rows = rowOrColumnPattern.findFirstMatchIn(toCheck.head)
    val columns = rowOrColumnPattern.findFirstMatchIn(toCheck(1))
    if(rows.isEmpty | columns.isEmpty){
      throw new IllegalArgumentException("First two lines do not contain rows and columns")
    }
    else{
      List(rows.get.group(0), columns.get.group(0))
    }
  }
}

/** Stores a Set of Points and an upper case character that the points contain */
case class Graph(points: Set[Point], char: Character)
/** Point coordinates in a 2D plane */
case class Point(x: Int, y: Int)