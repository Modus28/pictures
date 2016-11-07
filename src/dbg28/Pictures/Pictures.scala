package dbg28.Pictures

import dbg28.Graph


/**
  * Pictures: Accepts Lists of Strings describing pictures and converts them into relations
  *
  *
  */
object Pictures{

  var layerOrder: List[Char] = List()



  // check the intersect of each set inside merged for which graph is layered over another, and add a relation for it
  def extractLowestLayer(mergedGraph: List[Graph], graphsList: List[Graph]): Unit = {
      val lowestLayer: List[Graph] = mergedGraph.filter(isLowestLayer(_, graphsList))
      assert (lowestLayer.size equals 1) // Only one can be the lowest layer
      
  }


  def isLowestLayer(layer: Graph, graphsList: List[Graph]): Boolean = {
    val filteredGraph: List[Graph] = graphsList.filter(_.char != layer.char)
    filteredGraph.forall(p => (p.points intersect layer.points).isEmpty)
  }


  // Merge relations into a single ordered string




}

