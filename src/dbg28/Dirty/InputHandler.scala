package dbg28.Dirty

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

  /**
    *  Method that stores stdIn until it receives two empty lines
    * @return user input
    */
  private def readInput: Seq[String] = {
    @tailrec
    def readRecursively(input: Seq[String], wasEmpty: Boolean): Seq[String] = {
      val currentLine = scala.io.StdIn.readLine()
      val currentlyEmpty = currentLine.isEmpty
      if (currentlyEmpty && wasEmpty) input else readRecursively(currentLine +: input, currentlyEmpty)
    }
    readRecursively(Seq[String](), wasEmpty = false)
  }


  def main(args: Array[String]) {
    val userInput: Seq[String] = readInput
    DataPackaging.processInput(userInput.toList)
  }
}
