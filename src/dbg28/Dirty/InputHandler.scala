package dbg28.Dirty

import dbg28.InputVerification
import annotation.tailrec
import scala.io.StdIn.readLine
/**
  * EECS 293
  * Created by Daniel on 11/3/2016.
  * dbg28@case.edu
  *
  * Accepts user input and passes it to DataPackaging
  * On the untrusted side of the barricade
  */
object InputHandler {

  def main(args: Array[String]) {
    val userInput: Seq[String] = readInput
    processInput(userInput.toList)
  }

  /**
    * Stores stdIn until it receives two empty lines
    *
    * @return user input
    */
  private def readInput: Seq[String] = {
    /* Reads the next line and adds it to a list, halts when we find two empty lines in a row*/
    @tailrec
    def readRecursively(input: Seq[String], wasEmpty: Boolean): Seq[String] = {
      val currentLine = readLine()
      val currentlyEmpty = currentLine.isEmpty
      if (currentlyEmpty && wasEmpty) input.reverse else readRecursively(currentLine +: input, currentlyEmpty)
    }
    readRecursively(Seq[String](), wasEmpty = false)
  }

  /**
    * Accepts user input, converts it to a usable form and sends it to be verified
    *
    * @param input user input in List of Strings form
    */
  def processInput(input: List[String]): Unit = {
    val dimensions = DataPackaging.parseDimensions(input)

    InputVerification.setDimensions(dimensions.head, dimensions(1))
    val rows = dimensions.head.toInt
    val columns = dimensions(1).toInt
    val graphStrings = DataPackaging.partitionStringLists(input, rows)
    val graphs = DataPackaging.stringsToGraphs(graphStrings dropRight 1)
    val layeredGraphs = DataPackaging.stringListToLayeredGraph(graphStrings.last)
    InputVerification.addGraphs(graphs)
    InputVerification.addLayeredGraph(layeredGraphs)

    InputVerification.verify()
    InputVerification.resetVerificationState()

    /*println(graphs.mkString("\n"))
    println()
    println(layeredGraphs.mkString("\n"))*/
  }

  /** TestHook: Subclass for testing private methods
    *
    */
  object TestHook {
    // Calls readInput
    def readInputAccessor: Seq[String] = readInput
  }
}