package dbg28.Test

import java.io.{ByteArrayInputStream, ByteArrayOutputStream, InputStream}
import java.nio.charset.StandardCharsets
import org.junit.Assert._
import org.junit.{After, Before, Test}


/**
  * EECS 293
  * Created by Daniel on 11/7/2016.
  * dbg28@case.edu
  *
  * Tester for the general program execution, covers 90% of all code coverage
  */
class InputHandlerTest {

  val str =
    "9" + "\n" +
    "8" + "\n" +
    ". . . . . . . .\n" +
    "E E E E E E . .\n" +
    "E . . . . E . .\n" +
    "E . . . . E . .\n" +
    "E . . . . E . .\n" +
    "E . . . . E . .\n" +
    "E . . . . E . .\n" +
    "E . . . . E . .\n" +
    "E E E E E E . .\n\n" +
    ". . . . . . . .\n" +
    ". . . . . . . .\n" +
    "D D D D D D . .\n" +
    "D . . . . D . .\n" +
    "D . . . . D . .\n" +
    "D . . . . D . .\n" +
    "D D D D D D . .\n" +
    ". . . . . . . .\n" +
    ". . . . . . . .\n\n" +
    ". . . . . . . .\n" +
    ". . . . . . . .\n" +
    ". . . . . . . .\n" +
    ". . . . . . . .\n" +
    ". . . . A A A A\n" +
    ". . . . A . . A\n" +
    ". . . . A . . A\n" +
    ". . . . A A A A\n" +
    ". . . . . . . .\n\n" +
    ". . . . . . . .\n" +
    ". . B B B B . .\n" +
    ". . B . . B . .\n" +
    ". . B . . B . .\n" +
    ". . B . . B . .\n" +
    ". . B B B B . .\n" +
    ". . . . . . . .\n" +
    ". . . . . . . .\n" +
    ". . . . . . . .\n\n" +
    ". C C C . . . .\n" +
    ". C . C . . . .\n" +
    ". C . C . . . .\n" +
    ". C C C . . . .\n" +
    ". . . . . . . .\n" +
    ". . . . . . . .\n" +
    ". . . . . . . .\n" +
    ". . . . . . . .\n" +
    ". . . . . . . .\n\n" +
    ". C C C . . . .\n" +
    "E C B C B B . .\n" +
    "D C B C D B . .\n" +
    "D C C C . B . .\n" +
    "D . B . A B A A\n" +
    "D . B B B B . A\n" +
    "D D D D A D . A\n" +
    "E . . . A A A A\n" +
    "E E E E E E . .\n\n\n"

  val inputHandler = dbg28.Dirty.InputHandler
  val testHook = inputHandler.TestHook
  var out = new ByteArrayOutputStream
  var err = new ByteArrayOutputStream
  var in: InputStream = new ByteArrayInputStream(str.getBytes(StandardCharsets.UTF_8))

  @Before
  def setup(): Unit ={
    out = new ByteArrayOutputStream
    err = new ByteArrayOutputStream
    in = new ByteArrayInputStream(str.getBytes(StandardCharsets.UTF_8))
  }

  @After
  def tearDown(): Unit = {
    out = null
    err = null
    in = null
  }




  /**
    * Tests readInput
    * All Data: No such thing as good/bad data
    * Branching: If condition both true and false at different times
    */
  @Test
  def testReadInput(): Unit = {
    Console.withIn(in) {
      Console.withOut(out) {
        val seq = testHook.readInputAccessor
        print(seq.mkString("\n"))
      }
    }
    assertEquals(str, out.toString + "\n\n")
    // add 2 new lines to account for readInput's double new line check
  }
  
  /**
    * Tests Main and Process Input
    * Tests the normal program execution
    * Good data: Correct graphs and layered output
    */
  @Test
  def testMainAndProcessLog(): Unit = {
    Console.withIn(in){
      Console.withOut(out){
        inputHandler.main(Array.empty[String])
      }
    }
    assertEquals("EDABC",out.toString)
  }
}
