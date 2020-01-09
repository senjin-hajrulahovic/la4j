package org.la4j

import java.util
import java.util.Random

import scala.collection.JavaConverters._

import org.la4j.vector.{DenseVector, SparseVector}

abstract class SVector(length: Int = 0) extends Iterable[Double] {
  def get(i: Int): Double
  def blankOfLength(length: Int): Vector

  override def slice(from: Int, until: Int): Vector = {
    val result: Vector = blankOfLength(until - from)
    from.until(until).foreach(i => result.set(i, get(i)))
    result
  }

  def sliceLeft(until: Int): Vector = {
    slice(0, until)
  }

  def sliceRight(from: Int): Vector = {
    slice(from, length)
  }
}

object SVector {
  def zero(length: Int): Vector = {
    if (length > 1000) SparseVector.zero(length) else DenseVector.zero(length)
  }

  def constant(length: Int, value: Double): Vector = {
    DenseVector.constant(length, value)
  }

  def unit(length: Int): Vector = {
    DenseVector.constant(length, 1.0)
  }

  def random(length: Int, random: Random): Vector = {
    DenseVector.random(length, random)
  }

  def fromArray(array: Array[Double]): Vector = {
    DenseVector.fromArray(array)
  }

  def fromCSV(csv: String): Vector = {
    val tokens: Seq[String] = csv.split(", ")
    val result: Vector = DenseVector.zero(tokens.length)
    tokens.map(_.toDouble).zipWithIndex.foreach { case (v, idx) => result.set(idx, v) }
    result
  }

  def fromCollection[T <: Number](list: util.Collection[T]): Vector = {
    DenseVector.fromCollection(list)
  }

  def fromMap[T <: Number](map: Map[Integer, T], length: Int): Vector = {
    SparseVector.fromMap(map.asJava, length)
  }
}
