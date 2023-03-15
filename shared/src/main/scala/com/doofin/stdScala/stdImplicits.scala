package com.doofin.stdScala

import scala.concurrent.{Await, Future}
import scala.concurrent.duration.Duration
import scala.language.implicitConversions

/** implicit conversions for number */
object stdImplicits {

  implicit def i2f(d: Int): Float = d.toFloat
  implicit def i2d(d: Int): Double = d.toDouble
  implicit def d2f(d: Double): Float = d.toFloat
  implicit def f2d(d: Float): Double = d.toDouble

}
