package dbg28

import dbg28.Clean.Pictures
import language.postfixOps


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
    val nonEmptyGraphs = !List(layeredGraphs, singleGraphs).forall(_.isEmpty)
    val noExcess = noExcessCharactersExist(layeredGraphs,singleGraphs)
    val sameSize = graphsAreSameSize(layeredGraphs, singleGraphs)
    val canBeSolved = isSolvable(singleGraphs)
    val hasMultipleSolutions = hasMultipleLowestLayers(layeredGraphs, singleGraphs)
    val verifications = List(nonEmptyGraphs, sameSize, noExcess, canBeSolved, hasMultipleSolutions)

    if(verifications.forall(_ equals true)){
      Pictures.solve(layeredGraphs, singleGraphs)
    }
    else{
      resetVerificationState()
      ErrorManager.reportError(this, "Some graph was malformed")
    }
  }


  /**
    * Determines if a graph is solvable, based on the following criteria:
    * Each Graph has points - Satisfied by the below criteria
    * Each Graph has at least one point that overlaps another Graph's point on the graph, indicating a direct chain of overlap
    * @param graphs the input graphs to check
    * @return
    */
  def isSolvable(graphs: List[Graph]): Boolean = {
      def hasAtLeastOneOverlap(g: Graph): Boolean = !graphs.forall(_.points.intersect(g.points).isEmpty)
      graphs.forall(hasAtLeastOneOverlap)
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

  /**
    * Determines if a graph input has multiple solutions at any time
    * @param layered the list of layered graphs
    * @param separated the list of separated graphs
    */
  def hasMultipleLowestLayers(layered: List[Graph], separated: List[Graph]): Boolean = {
    def isLowest(g: Graph) = separated.filter(_.char != g.char).forall(_.points intersect g.points isEmpty)
    layered.count(isLowest) equals 1
  }
}

