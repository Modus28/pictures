package dbg28

/**
  * EECS 293
  * Created by Daniel on 11/4/2016.
  * dbg28@case.edu
  *
  * Barricade: Accepts Dirty input from DataPackaging, sanitizes it, and passes it on to Pictures
  */
object InputVerification {
  var columns = 0
  var rows = 0
  var graphs: List[Graph] = List.empty[Graph]
  var merged: Array[Array[Character]] = _


  def setDimensions(columns: String, rows: String): Unit = {
    this.columns = Integer.parseInt(columns)
    this.rows = Integer.parseInt(rows)
    // add verification checks
  }

  def addMergedGraph(charArray: Array[Array[Character]]): Unit = {
    merged = charArray
  }

  def resetVerificationState(): Unit = {
    // wipe everything
    columns = 0
    rows = 0
    graphs = null
    merged = null
  }

}

