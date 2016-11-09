package dbg28.Test

import java.io.ByteArrayOutputStream

import org.junit.Assert._
import org.junit.{After, Before, Rule, Test}
import dbg28.Dirty.DataPackaging
import dbg28.{ErrorManager, Graph, Point}

/**
  * EECS 293
  * Created by Daniel on 11/8/2016.
  * dbg28@case.edu
  */
class DataPackagingTest {

  // Fields with default testing data
  val listOfGraphStrings =
    "List(. . . . . . . ., E E E E E E . ., E . . . . E . ., E . . . . E . ., E . . . . E . ., E . . . . E . ., E . . . . E . ., E . . . . E . ., E E E E E E . .)\n" +
    "List(. . . . . . . ., . . . . . . . ., D D D D D D . ., D . . . . D . ., D . . . . D . ., D . . . . D . ., D D D D D D . ., . . . . . . . ., . . . . . . . .)\n" +
    "List(. . . . . . . ., . . . . . . . ., . . . . . . . ., . . . . . . . ., . . . . A A A A, . . . . A . . A, . . . . A . . A, . . . . A A A A, . . . . . . . .)\n" +
    "List(. . . . . . . ., . . B B B B . ., . . B . . B . ., . . B . . B . ., . . B . . B . ., . . B B B B . ., . . . . . . . ., . . . . . . . ., . . . . . . . .)\n" +
    "List(. C C C . . . ., . C . C . . . ., . C . C . . . ., . C C C . . . ., . . . . . . . ., . . . . . . . ., . . . . . . . ., . . . . . . . ., . . . . . . . .)\n" +
    "List(. C C C . . . ., E C B C B B . ., D C B C D B . ., D C C C . B . ., D . B . A B A A, D . B B B B . A, D D D D A D . A, E . . . A A A A, E E E E E E . .)"
  val eGraphString =
    ". . . . . . . .\n" +
      "E E E E E E . .\n" +
      "E . . . . E . .\n" +
      "E . . . . E . .\n" +
      "E . . . . E . .\n" +
      "E . . . . E . .\n" +
      "E . . . . E . .\n" +
      "E . . . . E . .\n" +
      "E E E E E E . .\n\n"
  val eGraphPoints = Set(Point(1,8), Point(5,0), Point(8,10), Point(3,10), Point(6,10), Point(4,0), Point(4,10),
    Point(2,10), Point(2,0), Point(3,0), Point(8,0), Point(5,10), Point(1,6), Point(1,10), Point(1,4),
    Point(8,2), Point(8,8), Point(7,10), Point(8,4), Point(1,2), Point(6,0), Point(8,6), Point(1,0), Point(7,0))
  val layeredGraphStr =
      ". C C C . . . .\n" +
      "E C B C B B . .\n" +
      "D C B C D B . .\n" +
      "D C C C . B . .\n" +
      "D . B . A B A A\n" +
      "D . B B B B . A\n" +
      "D D D D A D . A\n" +
      "E . . . A A A A\n" +
      "E E E E E E . .\n"
  val inputHandlerTest = new InputHandlerTest // to access big strings inside it
  var out = new ByteArrayOutputStream

  // Tests

  @After
  def setUp(): Unit = {
    out = new ByteArrayOutputStream
    ErrorManager.errorState = false
  }

  @After
  def tearDown(): Unit = {
    out = null
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
    val strList: List[String] = inputHandlerTest.str.split("\n").toList
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
    val strList: List[String] = inputHandlerTest.str.split("\n").toList
    Console.withOut(out) {
      DataPackaging.partitionStringLists(strList, 0)
    }
    assertEquals("error", out.toString)
  }

  /**
    * Tests parseDimensions
    * Branching: If condition false
    */
  @Test
  def parseDimensionsGoodData(): Unit = {
    val rowColList = List("4", "5")
    Console.withOut(out) {
      assertEquals(List(4, 5),DataPackaging.parseDimensions(rowColList))
    }
    assertEquals("", out.toString)
  }


  /**
    * Tests parseDimensions
    * Branching: If condition true
    * Bad data: One input is not a
    */
  @Test
  def parseDimensionsBadData(): Unit = {
    val rowColList = List("c", "5")
    Console.withOut(out) {
      assertEquals(null,DataPackaging.parseDimensions(rowColList))
    }
    assertEquals("error", out.toString)
  }

  /**
    * tests stringListToGraph:
    * Good Data: Input is a List of Strings and a character that is in them
    */
  @Test
  def stringListToGraphGoodData(): Unit = {
    val s: List[String] = eGraphString.split("\n").toList
    val g: Graph = DataPackaging.stringListToGraph(s,'E')
    assertEquals(eGraphPoints,g.points.intersect(eGraphPoints))
    assertEquals(Set.empty[Point], g.points diff eGraphPoints)
  }

  /**
    * tests stringListToGraph:
    * Bad Data: The character input is not contained in the List of Strings
    */
  @Test
  def stringListToGraphBadData(): Unit = {
    val eGraphStringList: List[String] = eGraphString.split("\n").toList
    val graph: Graph = DataPackaging.stringListToGraph(eGraphStringList,'F')
    assertEquals(Set.empty[Point], graph.points.intersect(eGraphPoints))
    assertEquals(Set.empty[Point], graph.points diff eGraphPoints)
  }

  /**
    * Tests stringListToLayeredGraph
    * Good/Bad Data: All data is compatible with this method except null
    */
  @Test
  def stringListToLayeredGraphData(): Unit = {
      val layeredGraphStringList: List[String] = layeredGraphStr.split("\n").toList
      val graphs: List[Graph] = DataPackaging.stringListToLayeredGraph(layeredGraphStringList)
      val numOfPoints = graphs.map(g => g.points.size).sum
       assertEquals(50, numOfPoints)
  }
}
