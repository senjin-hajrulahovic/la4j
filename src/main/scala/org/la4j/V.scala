package org.la4j

object V {
  def v(values: Double*): Vector = {
    Vector.fromArray(values.toArray)
  }

  def vs(values: Double*): Iterable[Vector] = {
    List(
      v(values: _*).to(Vectors.BASIC),
      v(values: _*).to(Vectors.COMPRESSED)
    )
  }

  def vz(length: Int): Unit = {
    Vector.zero(length)
  }
}
