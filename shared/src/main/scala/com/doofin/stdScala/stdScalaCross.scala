package com.doofin.stdScala
import macros._

/** import this for cross project .or stdScalaJvm for jvm specific ops */
object stdScalaCross
    extends miscUtils
    // with types
    with dataOps
    with math
    // with stdSerializers
    with stdImplicits {

  /** like rust's dbg! ,support several variables : dbg(a, b, c) . if there are
    * several variables,only prints the line of the first one
    */
  inline def dbg(inline exprs: Any*): Unit = ${ debugImpl('exprs) }
}
