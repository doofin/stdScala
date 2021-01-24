package com.doofin.stdScala

import shapeless._
import poly._
import syntax.std.tuple._
import ops.tuple.FlatMapper

trait types {
  type SeqTup[a, b] = Seq[(a, b)]
  type |[a, b] = Either[a, b]
//  sealed trait coprod3[a, b, c]

  val unit: Unit = ()
  def undefined: Nothing = ???

  trait FlattenTuple extends Poly1 {
    implicit def default[T] = at[T](Tuple1.apply)
  }

  /** flatten a nested tuple completely*/
  object flattenTuple extends FlattenTuple {
    implicit def caseTuple[P <: Product](
        implicit lfm: Lazy[FlatMapper[P, flattenTuple.type]]
    ) =
      at[P](x => lfm.value(x))
  }

}
