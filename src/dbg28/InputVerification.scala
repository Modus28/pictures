package dbg28


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
  var singleGraphs: List[Graph] = List.empty[Graph] // should assert sizes are the same in verify
  var layeredGraphs: List[Graph] = List.empty[Graph]

  // Setters
  def setDimensions(columns: Int, rows: Int): Unit = this.columns = columns; this.rows = rows
  def setLayeredGraph(layeredGraph: List[Graph]): Unit = layeredGraphs = layeredGraph
  def setGraphs(graphsList: List[Graph]): Unit = singleGraphs = graphsList


  // Other methods
  def resetVerificationState(): Unit = {
    // wipe everything
    columns = 0
    rows = 0
    singleGraphs = null
    layeredGraphs = null
  }

  def verify(): Unit = {
    //if conditions pass, add data to Pictures
    // should verify that no excess characters exist in list
    // should verify that all points in original set exist in layered set

    if(Option(columns).isDefined && Option(columns).isDefined && Option(singleGraphs).isDefined && Option(layeredGraphs).isDefined){
      dbg28.Clean.Pictures.solve(layeredGraphs, singleGraphs)
    }
    else{
      ErrorManager.reportError(this, "Some graph was malformed")
    }
  }

}

