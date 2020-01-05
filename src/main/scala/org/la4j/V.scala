package org.la4j

import collection.JavaConverters._
import scala.annotation.varargs

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
