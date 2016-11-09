package dbg28.Test

import dbg28.{Graph, InputVerification, Point}
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
    * Branching: If condition true
    */
  @Test
  def testVerifyTestsPass(): Unit = {

  }
}
