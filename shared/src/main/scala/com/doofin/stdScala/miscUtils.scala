package com.doofin.stdScala

import java.util.Base64._
import java.util.Date
import java.util.concurrent.TimeUnit
import scala.util.{Failure, Success, Try}

import scala.language.implicitConversions

/** print utils,etc */
 trait miscUtils {
  def getHomeDir: String = System.getProperty("user.home")
  def getCurrDir: String = System.getProperty("user.dir")
  val currTime = () => System.currentTimeMillis()
  def getDateStr = new Date().toLocaleString
  def printdate() = lg(getDateStr)

  /** throw new RuntimeException if codeBlock fails */
  def throwE[t](codeBlock: => t, msg: String = "") = {
    Try(codeBlock) match {
      case Failure(exception) =>
        throw new RuntimeException(s"$msg , ${exception.getMessage}")
      case Success(value) => value
    }

  }

  def pt(x: Any*) = {
    // x.foldLeft("")((a, b) => a + b.toString())
    val str = x.reduceOption((a, b) => s"$a,$b").getOrElse("")
    println(str)
    // print(1, 2, "aa", "aagc")
  }

  /** simple log */
  def lg[t](x: t) = { println(x.toString); x }

  /** pretty print */
  def pp(x: Any, w: Int = 100): Unit =
    pprint.PPrinter.BlackWhite.pprintln(x, w, 100000)

  def ppc(x: Any, w: Int = 100): Unit =
    pprint.pprintln(x, w, 100000)

  def time[R](block: => R): R = {
    val t0 = System.nanoTime()
    val result = block // call-by-name
    val t1 = System.nanoTime()
    val ns: Long = t1 - t0
    val s: Long = TimeUnit.SECONDS.convert(ns, TimeUnit.NANOSECONDS)
    println(s"Elapsed time: ${ns.toString} ns , ${s} s")
    result
  }

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
