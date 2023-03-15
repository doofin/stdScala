package com.doofin.stdScala
import macros.*
import systemEnvir.*

object debugAndLog {

  /** like rust's dbg! ,support several variables : dbg(a, b, c) . if there are
    * several variables,only prints the line of the first one
    */
  inline def dbg(inline exprs: Any*): Unit = ${ debugImpl('exprs) }

  /** same as scala 2 println supporting multiple arguments */
  def pt(x: Any*) = {
    val str = x.reduceOption((a, b) => s"$a,$b").getOrElse("")
    println(str)
  }

  /** log and returns original value */
  def lg[T](x: T): T = { println(x.toString); x }

  /** pretty print */
  def pp(x: Any, w: Int = 100): Unit =
    pprint.PPrinter.BlackWhite.pprintln(x, w, 100000)

  def ppc(x: Any, w: Int = 100): Unit =
    pprint.pprintln(x, w, 100000)

  def encloseDebug[t](d: String, printTime: Boolean = false)(
      codeBlock: => t
  ): t = {
    val st = () => if (printTime) getDateStr else ""
    println(s"---------------- $d start ------" + st())
    val r = codeBlock
    println(s"---------------- $d end -----" + st())
    r
  }

}
