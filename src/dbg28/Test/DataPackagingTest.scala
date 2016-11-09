package dbg28.Test

import java.io.ByteArrayOutputStream

import org.junit.Assert._
import org.junit.{After, Before, Rule, Test}
import dbg28.Dirty.DataPackaging
import dbg28.ErrorManager
import dbg28.Test.InputHandlerTest
/**
  * EECS 293
  * Created by Daniel on 11/8/2016.
  * dbg28@case.edu
  */
class DataPackagingTest {

  val listOfGraphStrings =
    "List(. . . . . . . ., E E E E E E . ., E . . . . E . ., E . . . . E . ., E . . . . E . ., E . . . . E . ., E . . . . E . ., E . . . . E . ., E E E E E E . .)\n" +
    "List(. . . . . . . ., . . . . . . . ., D D D D D D . ., D . . . . D . ., D . . . . D . ., D . . . . D . ., D D D D D D . ., . . . . . . . ., . . . . . . . .)\n" +
    "List(. . . . . . . ., . . . . . . . ., . . . . . . . ., . . . . . . . ., . . . . A A A A, . . . . A . . A, . . . . A . . A, . . . . A A A A, . . . . . . . .)\n" +
    "List(. . . . . . . ., . . B B B B . ., . . B . . B . ., . . B . . B . ., . . B . . B . ., . . B B B B . ., . . . . . . . ., . . . . . . . ., . . . . . . . .)\n" +
    "List(. C C C . . . ., . C . C . . . ., . C . C . . . ., . C C C . . . ., . . . . . . . ., . . . . . . . ., . . . . . . . ., . . . . . . . ., . . . . . . . .)\n" +
    "List(. C C C . . . ., E C B C B B . ., D C B C D B . ., D C C C . B . ., D . B . A B A A, D . B B B B . A, D D D D A D . A, E . . . A A A A, E E E E E E . .)"


  @After
  def resetErrorState(): Unit = {
    ErrorManager.errorState = false
  }
  /**
    * Tests partitionStringLists
    * Good Data/Bad Data: Any list of Strings is valid input
    * Good Data: Rows is a positive integer
    * Branching: Rows >= 1
    */
  @Test
  def partitionStringListsGoodData(): Unit = {
    val strList: List[String] = InputHandlerTest.str.split("\n").toList
    val graphStrings = DataPackaging.partitionStringLists(strList, 9).mkString("\n")
    assertEquals(listOfGraphStrings, graphStrings)
  }

  /**
    * Tests partitionStringLists
    * Good Data/Bad Data: Any list of Strings is valid input
    * Bad Data: Rows is an integer but not positive
    * Branching: Rows < 1
    */
  @Test
  def partitionStringListsBadData(): Unit = {
    val strList: List[String] = InputHandlerTest.str.split("\n").toList
    val out = new ByteArrayOutputStream
    val graphStrings = DataPackaging.partitionStringLists(strList, 0)
    assert(ErrorManager.errorState)
  }

}
