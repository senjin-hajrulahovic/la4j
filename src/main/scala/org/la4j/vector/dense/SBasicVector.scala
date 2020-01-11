package org.la4j.vector.dense

import java.io.InputStream
import java.nio.ByteBuffer
import java.util

import org.la4j.Vectors
import org.la4j.vector.{DenseVector, dense}

import scala.util.Random

import org.la4j.{Vector => JVector}
import scala.collection.JavaConverters._

class SBasicVector(self: Array[Double]) extends DenseVector{

  def this(length: Int = 0) {
    this(new Array[Double](length))
  }

  override def get(i: Int): Double = self(i)
  override def set(i: Int, value: Double): Unit = self(i, value)
  override def swapElements(i: Int, j: Int): Unit = {
    if (i != j) {
      val d: Double = self(i)
      self(i, self(j))
      self(j, d)
    }
  }

  override copyOfLength(length) {

  }

}

object SBasicVector {
  private val VectorTag: Byte = 0x00

  def zero(length: Int): SBasicVector = new SBasicVector(length)

  def constant(length: Int, value: Double): SBasicVector = {
    val array: Array[Double] = Array.fill(length)(value)
    new SBasicVector(array)
  }

  def unit(length: Int): SBasicVector = {
    SBasicVector.constant(length, 1.0)
  }

  def random(length: Int, random: Random): SBasicVector = {
    val array = new Array[Double](length).map(_ => random.nextDouble())
    new SBasicVector(length)
  }

  def fromArray(array: Array[Double]): SBasicVector = {
    new SBasicVector(array)
  }

  def fromBinary(array: Array[Byte]): SBasicVector = {
    val buffer: ByteBuffer = ByteBuffer.wrap(array)

    if (buffer.get() == VectorTag) {
      val array: Array[Double] = new Array[Double](buffer.getInt())
      new SBasicVector(array.map(_ => buffer.getDouble()))
    } else {
      throw new IllegalArgumentException("Can not decode BasicVector from the given byte array.")
    }
  }

  // todo return SBasicVector
  def fromCSV(csv: String): BasicVector = {
    JVector.fromCSV(csv).to(Vectors.BASIC)
  }

  def fromMatrixMarket(is: InputStream): BasicVector = {
    JVector.fromMatrixMarket(is).to(Vectors.BASIC)
  }

  def fromCollection[T <: Number](list: util.Collection[T]): SBasicVector = {
    fromArray(list.asScala.map(_.doubleValue()).toArray)
  }

}

