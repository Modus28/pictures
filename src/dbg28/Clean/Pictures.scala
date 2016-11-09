package dbg28.Clean

import dbg28.Graph
import language.postfixOps

/**
  * Pictures: Accepts Lists of Strings describing pictures and converts them into relations
  *
  *
  */
object Pictures{

  // Solving methods

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

    // Purges graphs with a given character from the lists, and adds the character to the layer order
    def removeAndUpdateCharacterStatus(char: Char): Unit = {
        mergedCopy = mergedCopy diff mergedCopy.filter(_.char equals char)
        graphCopy = graphCopy diff graphCopy.filter(_.char equals char)
        layerOrder = layerOrder ::: List(char)
    }

    for (_ <- mergedCopy) { removeAndUpdateCharacterStatus(lowestLayeredCharacter(mergedCopy, graphCopy)) }
    print(layerOrder.mkString.toString)
  }

  /**
    * Finds the lowest layered character from a merged graph and a list of unmerged graphs
    * @param mergedGraph the merged graph to remove from
    * @param graphsList the unmerged graphs to search through
    * @return
    */
  def lowestLayeredCharacter(mergedGraph: List[Graph], graphsList: List[Graph]): Char = {
      val lowestLayer: List[Graph] = mergedGraph.filter(isLowestLayer(_, graphsList))
      assert (lowestLayer.size equals 1) // Only one can be the lowest layer
      lowestLayer.head.char
  }

  /**
    * Determines if an unmerged graph is the lowest layer of a merged graph
    * @param layer the graph to check lowest layer status of
    * @param graphsList the list of graphs to search through
    * @return if a graph is the lowest layer of a merged graph
    */
  def isLowestLayer(layer: Graph, graphsList: List[Graph]): Boolean = {
    graphsList.filter(_.char != layer.char).forall(_.points intersect layer.points isEmpty)
  }
}

