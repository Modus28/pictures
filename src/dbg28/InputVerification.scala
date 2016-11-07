package dbg28


/**
  * EECS 293
  * Created by Daniel on 11/4/2016.
  * dbg28@case.edu
  *
  * Barricade: Accepts Dirty input from DataPackaging, sanitizes it, and passes it on to Pictures
  */
object InputVerification {
  var columns: Int = _
  var rows: Int = _
  var singleGraphs: List[Graph] = List.empty[Graph]
  var layeredGraphs: List[Graph] = List.empty[Graph]


  def setDimensions(columns: String, rows: String): Unit = {
    this.columns = Integer.parseInt(columns)
    this.rows = Integer.parseInt(rows)
    // add verification checks
  }

  def addLayeredGraph(layeredGraph: List[Graph]): Unit = layeredGraphs = layeredGraph

  def addGraphs(graphsList: List[Graph]): Unit = singleGraphs = graphsList

  def resetVerificationState(): Unit = {
    // wipe everything
    columns = 0
    rows = 0
    singleGraphs = null
    layeredGraphs = null
  }

  def verify(): Unit = {
    //if conditions pass, add data to Pictures
    if(Option(columns).isDefined && Option(rows).isDefined && Option(singleGraphs).isDefined && Option(layeredGraphs).isDefined){
      println(dbg28.Clean.Pictures.solve(layeredGraphs, singleGraphs))
    }
    // If fail, print error and do nothing
  }

}

