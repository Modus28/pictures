package dbg28.Clean

import dbg28.Graph


/**
  * Pictures: Accepts Lists of Strings describing pictures and converts them into relations
  *
  *
  */
object Pictures{


  /**
    * Solve a given Merged Picture and its separated form
    * @param mergedGraph the merged picture
    * @param graphList the separated picture
    * @return the order of the layers by character
    */
  def solve(mergedGraph: List[Graph], graphList: List[Graph]): Unit = {
    var layerOrder: List[Char] = List()
    var mergedCopy = mergedGraph
    var graphCopy = graphList

    def extractLowestLayer(char: Char): Unit = {
        mergedCopy = mergedCopy diff mergedCopy.filter(_.char equals char)
        graphCopy = graphCopy diff graphCopy.filter(_.char equals char)
        layerOrder = layerOrder ::: List(char)
    }

    for (_ <- mergedCopy) { extractLowestLayer(lowestLayeredCharacter(mergedCopy, graphCopy)) }
    print(layerOrder.mkString.toString)
  }



  // check the intersect of each set inside merged for which graph is layered over another, and add a relation for it
  def lowestLayeredCharacter(mergedGraph: List[Graph], graphsList: List[Graph]): Char = {
      val lowestLayer: List[Graph] = mergedGraph.filter(isLowestLayer(_, graphsList))
      assert (lowestLayer.size equals 1) // Only one can be the lowest layer
      lowestLayer.head.char
  }


  def isLowestLayer(layer: Graph, graphsList: List[Graph]): Boolean = {
    val filteredGraph: List[Graph] = graphsList.filter(_.char != layer.char)
    filteredGraph.forall(p => (p.points intersect layer.points).isEmpty)
  }


  // Merge relations into a single ordered string




}

