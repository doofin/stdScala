package com.doofin.stdScala

import scala.language.postfixOps
import scala.math._
import stdImplicits.*
import dataOps.*

trait math {

  /** boolean ops */
  /** true if one of args is true */
  def or(bs: Boolean*): Boolean = bs.exists(x => x)

  /** true if all are true */
  def all(bs: Boolean*): Boolean = bs.forall(x => x)

  /** trigometry:convert tan to sin */
  def tan2sin(tan: Double) = {
    val tan2 = tan ^ 2
    val s2 = tan2 / (1 + tan2)
    sqrt(s2)
  }

  /** trigometry:convert tan to cos */
  def tan2cos(tan: Double) = {
    val s2 = 1 / (1 + (tan ^ 2))
    sqrt(s2)
  }

  /** statistics */
  def avg(xs: Seq[Float]) = {
    val len = xs.length
    xs.sum / len
  }

  /** linear algebra */
  object Vector2 {

    /** additive identity */
    def addId() = Vector2(0, 0)

    /** multiplicative identity */
    def mulId() = Vector2(1, 1)
  }

  /** 2 dimensional vector */
  case class Vector2(x: Float, y: Float) {

    /** self properties */
    def isUnit = x == 0 & y == 0

    /** round this float vector to int vector */
    def round2Int = Vector2(x.floor, y.floor)

    def magnitude: Float = Math.sqrt(x * x + y * y).toFloat
    def perpendicular = Vector2(-y, x)
    def abs = Vector2(x.abs, y abs)

    def tan = y / x
    def cos = tan2cos(tan)
    def sin = tan2sin(tan)

    def normalized: Vector2 =
      if (this.isUnit) this else this / magnitude

    def negate: Vector2 = Vector2(-x, -y)
    def round: Vector2 = Vector2(
      scala.math.round(x),
      scala.math.round(y)
    )

    /** ops with other vectors */
    def mul(vec: Vector2) = Vector2(x * vec.x, y * vec.y)
    def *(vec: Vector2) = Vector2(x * vec.x, y * vec.y)

    def +(vec: Vector2): Vector2 = Vector2(this.x + vec.x, this.y + vec.y)
    def subtract(vec: Vector2): Vector2 =
      Vector2(this.x - vec.x, this.y - vec.y)
    def -(vec: Vector2): Vector2 = subtract(vec)
    def dotProduct(vec: Vector2): Float = x * vec.x + y * vec.y

    def distanceTo(vec: Vector2): Float = (this - vec).magnitude
    def distanceX(vec: Vector2): Float = (this.x - vec.x)
    def distanceY(vec: Vector2): Float = (this.y - vec.y)

    /** apply function */
    def apF(f: Float => Float): Vector2 = Vector2(f(x), f(y))

    /** ops with scalar */
    def mulScalar(s: Float): Vector2 = mul(Vector2(s, s))
    def addScalar(f: Float): Vector2 = this + Vector2(f, f)
    def /(f: Float): Vector2 = mulScalar(1 / f)

  }
  // def *(f: Float): Vector2 = mulScalar(f)
  // def +(n: Float): Vector2 = this + Vector2(n, n)

}
