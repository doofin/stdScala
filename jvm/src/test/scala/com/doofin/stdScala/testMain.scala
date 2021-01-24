package com.doofin.stdScala

import org.scalatest.FunSuite
class testMain extends FunSuite {
  test("shapeless") {

    val t1 = (1, ((2, 3), 4))
    val t1f = flattenTuple(t1)
    val t2 = (1, ((2, 3, (1, ((2, t1, 3), 4))), 4))
    val t2f = flattenTuple(t2)
    assert(t1f == (1, 2, 3, 4))
    assert(t2f == (1, 2, 3, 1, 2, 1, 2, 3, 4, 3, 4, 4))
  }
}
