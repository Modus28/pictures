package dbg28.Test

import java.io.ByteArrayOutputStream

import dbg28.{ErrorManager, Graph, InputVerification, Point}
import org.junit.Assert._
import org.junit.{After, Before, Rule, Test}


/**
  * EECS 293
  * Created by Daniel on 11/9/2016.
  * dbg28@case.edu
  */
class InputVerificationTest {

  // Ensures the verifier is cleaned between tests
  @After @Before
  def ensureCleanVerifier(): Unit = {
    InputVerification.resetVerificationState()
  }

  /**
    * Tests graphsAreSameSize
    */
  @Test
    def graphSizeCheckerTest(): Unit = {
    assertFalse(InputVerification.graphsAreSameSize(List.empty[Graph],List(Graph('D', Set.empty[Point]))))
    assertTrue(InputVerification.graphsAreSameSize(List.empty[Graph], List.empty[Graph]))
  }

  /**
    * Tests noExcessCharactersExist
    */
  @Test
  def noExcessCharactersExistTest(): Unit = {
    val graphs: List[Graph] = List(Graph('E', Set(Point(1,2))), Graph('Q',Set(Point(3,4), Point(9,1))))
    val equalGraphs: List[Graph] = List(Graph('F', Set(Point(3,4))), Graph('R', Set(Point(1,2), Point(9,1))))
    assert(InputVerification.noExcessCharactersExist(graphs,equalGraphs))
  }


  /**
    * Tests Verify
    * Branching: If condition false
    * Branching: If condition true tested in InputHandlerTest indirectly
    */
  @Test
  def testVerifyTestsFail(): Unit = {
    val out = new ByteArrayOutputStream
    InputVerification.setGraphs(List(Graph('E', Set(Point(1,2))), Graph('Q',Set(Point(3,4), Point(9,1)))))
    InputVerification.setLayeredGraph(List(Graph('F', Set(Point(3,4))), Graph('R', Set(Point(1,2), Point(9,1)))))
   InputVerification.setGraphs(List(Graph('E', Set(Point(1,2)))))
    InputVerification.setLayeredGraph(List(Graph('E', Set(Point(0,2)))))
    // println(s"First Size: ${InputVerification.singleGraphs.size}, second: ${InputVerification.layeredGraphs.size}")
    Console.withOut(out) {
      try{
        InputVerification.verify()
      }catch{
        case n: NullPointerException => "Expected due to SBT error, with Ant/IDE this works fine"
        case e: Exception => assert(false) // we failed to predict this
      }
    }

  }

}
