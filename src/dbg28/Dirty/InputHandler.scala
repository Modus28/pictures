package dbg28.Dirty

import dbg28.{ErrorManager, InputVerification}

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
    val userInput: Seq[String] = readInput // Get input
    ErrorManager.errorState = false // Reset error states if bad last termination
    InputVerification.resetVerificationState()
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


  private def checkErrorState(): Unit = {
    if(ErrorManager.errorState){
      System.exit(10)
    }
    else{
      // continue
    }
  }
  /**
    * Accepts user input, converts it to a usable form and sends it to be verified
    *
    * @param input user input in List of Strings form
    */
  def processInput(input: List[String]): Unit = {
    val dimensions = DataPackaging.parseDimensions(input)
    checkErrorState()
    InputVerification.setDimensions(dimensions.head, dimensions(1))
    // Input Verification doesn't need to check these, as ParseDimensions already does.



    val graphStrings = DataPackaging.partitionStringLists(input, dimensions.head)
    //println("We partitioned string lists")
    checkErrorState()

    val graphs = DataPackaging.stringsToGraphs(graphStrings dropRight 1)
    val layeredGraphs = DataPackaging.stringListToLayeredGraph(graphStrings.last)
    //println("We converted strings to graphs")
    checkErrorState()
    InputVerification.setGraphs(graphs)
    InputVerification.setLayeredGraph(layeredGraphs)
    InputVerification.verify()
    //println("We did everything but solve")
    checkErrorState()

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
    def checkErrorStateAccessor(): Unit = checkErrorState()
  }
}