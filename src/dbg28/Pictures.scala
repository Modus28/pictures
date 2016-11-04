package dbg28

/**
  * Pictures: Accepts text files describing pictures and converts them into relations
  *
  */
class Pictures(graphs: List[Set[Point]], merged: Set[Point]){

  var relations: List[IsLayeredOverRelation] = List()


  // perform checks to test for invalid graphs
  // check the intersect of each set inside merged for which graph is layered over another, and add a relation for it
  // Merge relations into a single ordered string

}

case class IsLayeredOverRelation(a: Character, b: Character)