package dbg28.Pictures


/**
  * Pictures: Accepts Lists of Strings describing pictures and converts them into relations
  *
  *
  */
class Pictures(graphs: List[Graph] = List.empty[Graph], merged: Graph){

  var layerOrder: List[Character] = List()



  // check the intersect of each set inside merged for which graph is layered over another, and add a relation for it
  def extractLowestLayer(graphsList: List[Graph], mergedGraph: Graph){

  }
  // Merge relations into a single ordered string




}
/* Stores a Set of Points and an upper case character that the points contain */
case class Graph(points: Set[Point], char: Character)
/* Point coordinates in a 2D plane */
case class Point(x: Int, y: Int)
