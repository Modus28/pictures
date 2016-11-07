package dbg28.Dirty

import dbg28.InputVerification
import annotation.tailrec

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
    @tailrec
    def readRecursively(input: Seq[String], wasEmpty: Boolean): Seq[String] = {
      val currentLine = scala.io.StdIn.readLine()
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
    val rows = dimensions.head;
    val columns = dimensions(1)
    InputVerification.setDimensions(rows, columns)
    val graphStrings = DataPackaging.partitionInputIntoGraphStrings(input, rows)
    val graphs = DataPackaging.stringsToGraphs(graphStrings dropRight 1)
    InputVerification.addGraphs(graphs)
    val layeredGraphs = DataPackaging.stringListToLayeredGraph(graphStrings.last)
    InputVerification.addLayeredGraph(layeredGraphs)
    InputVerification.verify()

    println(graphs.mkString("\n"))
    println()
    println(layeredGraphs.mkString("\n"))
  }
}