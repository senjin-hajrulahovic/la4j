package org.la4j

import scala.annotation.varargs
import scala.collection.JavaConverters._

object V {
  @varargs def v(values: Double*): Vector = {
    Vector.fromArray(values.toArray)
  }

  @varargs def vs(values: Double*): java.lang.Iterable[Vector] = {
    List(
      v(values: _*).to(Vectors.BASIC),
      v(values: _*).to(Vectors.COMPRESSED)
    ).toIterable.asJava
  }

  def vz(length: Int): Vector = {
    Vector.zero(length)
  }
}
