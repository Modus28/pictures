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
  val merged: Graph = Graph(null, null)


  def setDimensions(columns: String, rows: String): Unit = {
    this.columns = Integer.parseInt(columns)
    this.rows = Integer.parseInt(rows)
    // add verification checks
  }

}
/** Stores a Set of Points and an upper case character that the points contain */
case class Graph(points: Set[Point], char: Character)
/** Point coordinates in a 2D plane */
case class Point(x: Int, y: Int)