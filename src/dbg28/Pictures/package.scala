package dbg28
import scala.language.implicitConversions
/**
  * EECS 293
  * Created by Daniel on 11/4/2016.
  * dbg28@case.edu
  *
  * Stores Implicit definitions and other package-wide information
  */
package object Pictures {
  implicit def graphToPointSet(g:Graph): Set[Point] = g.points
}
