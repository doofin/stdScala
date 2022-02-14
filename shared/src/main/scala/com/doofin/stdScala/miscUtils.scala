package com.doofin.stdScala

import scala.language.implicitConversions
import java.util.Base64._
import java.util.Date
import java.util.concurrent.TimeUnit
import scala.util.{Failure, Success, Try}

trait miscUtils {
  val currTime = () => System.currentTimeMillis()
  lazy val getHomeDir: String = System.getProperty("user.home")

  def base64encode = getEncoder
  def base64decode = getDecoder

  def throwE[t](f: => t, msg: String = "") = {
    Try(f) match {
      case Failure(exception) =>
        throw new RuntimeException(s"$msg , ${exception.getMessage}")
      case Success(value) => value
    }

  }

  /**simple log */
  def lg(x: Any)(implicit name: String = "") = println(s"$name : $x")

  val printLine = { () =>
    1 to 2 foreach (_ => print("\n"))
  }

  def getDateStr = new Date().toLocaleString
  def printdate() = lg(getDateStr)

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

  def encloseDebug[t](d: String, showTime: Boolean = true)(f: => t): t = {
    val st = () => if (showTime) getDateStr else ""
    echo(s"---------------- $d start ------" + st())
    val r = f
    echo(s"---------------- $d end -----" + st())
    r
  }

  val echo: String => Unit = println

}
