package dbg28

import scala.util.matching.Regex
/**
  * EECS 293
  * Created by Daniel on 11/3/2016.
  * dbg28@case.edu
  */
object InputHandler {
  val graphPattern = "/([A-Z*])/g".r
  val rowOrColumnPattern = "(\\d)".r

  def lineToPointSet(line: String): Set[Point] = {
    // convert String of a single line into a set of points for a letter
    Set[Point]()
  }

  def processInput(input: List[String]): Unit = {
    // Parse out the first two lines as integers and set those as locally stored variables
    // For each set of text between empty lines, create a Set of Points for it,
    // send all but the last one of the list of these sets to a Pictures process method
    // Send the final set, the completed graph, to a separate processing method
  }


  def main(args: Array[String]) {

    // Accept block of input as List of Strings and send it to processor method in Lists of lines
  }
}
