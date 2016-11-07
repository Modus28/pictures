package dbg28

/** Stores a Set of Points and an upper case character that the points contain */
case class Graph(char: Char, points: Set[Point]) extends Ordered[Graph] {
  override def compare(that: Graph): Int = this.char compare that.char
}
