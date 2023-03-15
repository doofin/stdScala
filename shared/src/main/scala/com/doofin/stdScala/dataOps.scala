package com.doofin.stdScala

import scala.reflect.ClassTag

/** operations on datatypes */
object dataOps {

  /** collect subtype */
  def collectT[A, T: ClassTag](xs: Seq[A]): Seq[T] = xs.collect { case x: T =>
    x
  }

  /** group by custom equality test */
  def groupByEqu[t](p: Seq[t], equF: t => t => Boolean): Seq[Seq[t]] = {
    // val equApp = equF(null)
    var ins: Set[t] = p.toSet
    var outs: Seq[Seq[t]] = Seq()
    def mv(): Unit = {
      if (ins.nonEmpty) {
        for {
          e0 <- ins
        } yield {
          val r = ins filter (e1 => equF(e0)(e1))

          ins --= r
          outs ++= Seq(r.toSeq)
        }
        mv()
      }
    }
    mv()
    outs.filter(_.nonEmpty)
  }

  def unzip4[a, b, c, d](
      xs: Seq[(a, b, c, d)]
  ): (Seq[a], Seq[b], Seq[c], Seq[d]) = {
    val eptTup: (Seq[a], Seq[b], Seq[c], Seq[d]) =
      (Seq.empty, Seq.empty, Seq.empty, Seq.empty)
    val r: (Seq[a], Seq[b], Seq[c], Seq[d]) = xs.foldLeft(eptTup) {
      (x: (Seq[a], Seq[b], Seq[c], Seq[d]), y) =>
        val (a, b, c, d) = x
        (a :+ y._1, b :+ y._2, c :+ y._3, d :+ y._4)
    }
    r
  }

  def unzip5[a, b, c, d, e](
      xs: Seq[(a, b, c, d, e)]
  ): (Seq[a], Seq[b], Seq[c], Seq[d], Seq[e]) = {
    val eptTup: (Seq[a], Seq[b], Seq[c], Seq[d], Seq[e]) =
      (Seq.empty, Seq.empty, Seq.empty, Seq.empty, Seq.empty)
    val r = xs.foldLeft(eptTup) {
      (x: (Seq[a], Seq[b], Seq[c], Seq[d], Seq[e]), y) =>
        val (a, b, c, d, e) = x
        (a :+ y._1, b :+ y._2, c :+ y._3, d :+ y._4, e :+ y._5)
    }
    r
  }

  extension (x: Double) {
    def ^(i: Double) = scala.math.pow(x, i)
  }

  extension (x: Boolean) { //
    /** usage : true ? (1,2) */
    def ?[t](a: t, b: t): t = if x then a else b
  }

  /* create tuples */
  extension [A](x: A) {
    inline def --[B](y: B): (A, B) = (x, y)
  }

  /* const map on tuples from Il_totore*/
  type Mapped[T <: Tuple, A, B] <: Tuple = T match
    case EmptyTuple => EmptyTuple
    case A *: tail  => B *: Mapped[tail, A, B]

  extension [T <: Tuple](tuple: T)
    /** map for fixed length vector */
    def mapConst[A, B](f: A => B): Mapped[T, A, B] = tuple match
      case EmptyTuple => EmptyTuple.asInstanceOf[Mapped[T, A, B]]
      case head *: tail =>
        (f(head.asInstanceOf[A]) *: tail.mapConst(f))
          .asInstanceOf[Mapped[T, A, B]]

}
