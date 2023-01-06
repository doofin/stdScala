package com.doofin.stdScala

import com.doofin.stdScala._
import macros._

trait Agg
    extends miscUtils
    with dataOps
    with math
    // with types
    // with stdSerializers
    with stdImplicits {

  /** like rust's dbg! ,support several variables : dbg(a, b, c) . if there are
    * several variables,only prints the line of the first one
    */
  inline def dbg(inline exprs: Any*): Unit = ${ debugImpl('exprs) }
}
