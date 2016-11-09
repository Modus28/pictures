package dbg28

import dbg28.Clean.Pictures


/**
  * EECS 293
  * Created by Daniel on 11/4/2016.
  * dbg28@case.edu
  *
  * Barricade: Accepts Dirty input from DataPackaging, sanitizes it, and passes it on to Pictures
  */
object InputVerification {
  // Fields
  var columns: Int = _
  var rows: Int = _
  var singleGraphs: List[Graph] = List.empty[Graph]
  var layeredGraphs: List[Graph] = List.empty[Graph]

  // Setters
  def setDimensions(columns: Int, rows: Int): Unit = this.columns = columns; this.rows = rows
  def setLayeredGraph(layeredGraph: List[Graph]): Unit = layeredGraphs = layeredGraph
  def setGraphs(graphsList: List[Graph]): Unit = singleGraphs = graphsList


  /**
    * Resets the Verifier to be used in future verifications cleanly
    * Should never be needed, but this makes it robust in the case of unusual soft crashes
    */
  def resetVerificationState(): Unit = {
    // wipe everything
    columns = 0
    rows = 0
    singleGraphs = null
    layeredGraphs = null
  }

  /**
    * Verify that data is valid and if so, solve the picture.
    */
  def verify(): Unit = {
    val noExcess = noExcessCharactersExist(layeredGraphs,singleGraphs)
    val sameSize = graphsAreSameSize(layeredGraphs, singleGraphs)
    val nonEmptyGraphs = !List(layeredGraphs, singleGraphs).forall(_.isEmpty)
    val verifications = List(noExcess, sameSize, nonEmptyGraphs)

    if(verifications.forall(_ equals true)){
      Pictures.solve(layeredGraphs, singleGraphs)
    }
    else{
      resetVerificationState()
      ErrorManager.reportError(this, "Some graph was malformed")
    }
  }


  def isSolvable(layered: List[Graph], separated: List[Graph]): Boolean = {

  }


  /**
    * Check that no points were removed or added from the graphs erroneously
    * Specifically, checks that the union of all original points equals the union of all merged points
    * @param layered the subgraphs of the layered picture
    * @param separate the graphs that were not layered
    * @return if the number of points in graphs of each type is the same
    */
  def noExcessCharactersExist(layered: List[Graph], separate: List[Graph]): Boolean = {
    separate.map(_.points).reduce(_ union _) equals layered.map(_.points).reduce(_ union _)
  }

  /**
    * Checks that the graphs have the same amount of different letters
    * @param graphsA first graph List to check size of
    * @param graphsB second graph List to check size of
    * @return if the sizes of the graphs are the same
    */
  def graphsAreSameSize(graphsA: List[Graph], graphsB: List[Graph]): Boolean = graphsA.size equals graphsB.size
}

