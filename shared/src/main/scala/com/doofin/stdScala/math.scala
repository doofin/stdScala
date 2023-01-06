package com.doofin.stdScala

import scala.language.postfixOps
import scala.math._
import com.doofin.stdScala.stdScalaCross.*

// import com.doofin.stdScala.All // import  com.doofin.stdScala.

trait math {

  /** boolean */
  /** true if one of args is true */
  def or(bs: Boolean*): Boolean = bs.exists(x => x)

  /** true if all are true */
  def all(bs: Boolean*): Boolean = bs.forall(x => x)

  /** trigometry */
  def tan2sin(tan: Double) = {
    val tan2 = tan ^ 2
    val s2 = tan2 / (1 + tan2)
    sqrt(s2)
  }

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
    def isUnit = x == 0 & y == 0
    def round2Int = Vector2(x.floor, y.floor)
    def magnitude: Float = Math.sqrt(x * x + y * y).toFloat
    def perpendicular = Vector2(-y, x)
    def abs = Vector2(x.abs, y abs)
    def tan = y / x
    def cos = tan2cos(tan)
    def sin = tan2sin(tan)
//    def atPhase1 = (x >= 0) & (y >= 0)
    def normalized: Vector2 =
      if (this.isUnit) this else this / magnitude
    def negate: Vector2 = Vector2(-x, -y)
    def round: Vector2 = Vector2(
      scala.math.round(x),
      scala.math.round(y)
    )

    def mul(vec: Vector2) = Vector2(x * vec.x, y * vec.y)
    def *(vec: Vector2) = Vector2(x * vec.x, y * vec.y)
    def add(vec: Vector2): Vector2 = Vector2(this.x + vec.x, this.y + vec.y)
    def +(vec: Vector2): Vector2 = add(vec)
    def subtract(vec: Vector2): Vector2 =
      Vector2(this.x - vec.x, this.y - vec.y)
    def -(vec: Vector2): Vector2 = subtract(vec)
    def apf(f: Float => Float): Vector2 = Vector2(f(x), f(y))

    def dotProduct(vec: Vector2): Float = x * vec.x + y * vec.y

    def *(f: Float): Vector2 = mulScalar(f)
    def +(n: Float): Vector2 = add(Vector2(n, n))
    def /(f: Float): Vector2 = mulScalar(1 / f)

    def mulScalar(s: Float): Vector2 = mul(Vector2(s, s))
    def addScalar(f: Float): Vector2 = add(Vector2(f, f))
    def distanceTo(vec: Vector2): Float = (this - vec).magnitude
    def distanceX(vec: Vector2): Float = (this.x - vec.x)
    def distanceY(vec: Vector2): Float = (this.y - vec.y)
  }

}
