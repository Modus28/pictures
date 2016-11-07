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
  val graphPattern: Regex = "([A-Z])".r
  val rowOrColumnPattern: Regex = "(\\d)".r




  def processInput(input: List[String]): Unit = {
    // Parse out the first two lines as integers and set those as locally stored variables
    val dimensions = parseDimensions(input)
    val rows  = dimensions.head
    val columns = dimensions(1)
    // Send Rows and Columns to InputVerification
    InputVerification.setDimensions(rows, columns)
    // The user input without the rows and columns
    val graphStrings = partitionGraphStrings(input, rows)
    val graphs = layerStringsToGraphs(graphStrings dropRight 1)
    println(graphs.mkString("\n"))

  }

  /**
    * Converts a List of Lists of Strings to a List of Graphs
    * @param allStrings the Strings to convert
    * @return List of Graphs
    */
  def layerStringsToGraphs(allStrings: List[List[String]]): List[Graph] = {
    allStrings map stringListToGraph
  }

  /**
    * Converts a single List of lines of text to a Graph
    * @param list the List to convert
    * @return Graph representation of input
    */
  def stringListToGraph(list: List[String]): Graph = {
    def isCapital(char: Character): Boolean =  graphPattern.findFirstIn(char.toString).isDefined
    val points = for( sIndex <- list.indices; charInd <- list(sIndex).indices if  isCapital(list(sIndex)(charInd))) yield Point(sIndex, charInd)
    val char: Character = list(points.head.x)(points.head.y)
    Graph(char, points.toSet)
  }

  /**
    *  Takes the total user input and partitions it into input for each graph
    * @param input the total input from user
    * @param rows amount of rows each graph should contain
    * @return a List of Lists of Strings, where each sublist is all the strings in one graph
    */
  private def partitionGraphStrings(input: List[String], rows: String):  List[List[String]] = {
    val inputWithoutDimensions = input diff List(input.head, input(1))
    val rowInt: Int = rows.toInt // maybe try catch this
    inputWithoutDimensions.sliding(rowInt, rowInt + 1).toList // check toInt failures
  }

  /**
    * Parses the List form of the user input to identify
    *
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
case class Graph(char: Character, points: Set[Point])
/** Point coordinates in a 2D plane */
case class Point(x: Int, y: Int)