package com.doofin.stdScala

import scala.concurrent.{Await, Future}
import scala.concurrent.duration.Duration
import scala.language.implicitConversions

object stdImplicits {
  implicit def d2s(d: Double): String = d.toString
  implicit def f2s(d: Float): String = d.toString

  implicit def i2f(d: Int): Float = d.toFloat
  implicit def i2d(d: Int) = d.toDouble
  implicit def d2f(d: Double): Float = d.toFloat
  implicit def f2d(d: Float): Double = d.toDouble

  implicit class doubleOps(x: Double) {
    def ^(i: Double) = scala.math.pow(x, i)
  }

  implicit class boolOps(x: Boolean) { //
    /**usage : (true ? "a") ("b") */
    def ?[t](a: t)(b: t): t = {
      if (x) a else b
    }
  }
  implicit def seq2str(xs: Seq[String]): String = xs reduce (_ + ",,,," + _)

  implicit final class ArrowAssoc2[A](x: A) {
    @inline def --[B](y: B): (A, B) = (x, y)
  }

  //  def awaitFuture[t](x: => Future[t]): t = Await result (x, Duration.Inf)
  implicit class futOps[t](f: Future[t]) {
    def unwarp = Await.result(f, Duration.Inf)
  }

}
