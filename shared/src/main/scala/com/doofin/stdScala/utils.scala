package com.doofin.stdScala

import scala.reflect.ClassTag

import java.util.Date
import java.util.concurrent.TimeUnit

import scala.concurrent.{Await, Future}
import scala.concurrent.duration.Duration
import scala.language.implicitConversions
import java.util.Base64._

//object sharedUtil extends sharedUtil //for use in shared

trait utils {
  val currTime = () => System.currentTimeMillis()

  def base64encode = getEncoder
  def base64decode = getDecoder

  def identityF[t](x: t) = x
  def sq(x: Int): Int = x * x
  def lg(x: Any)(implicit name: String = "") = println(s"$name : $x")
  def l[t](x: t) = { println(x.toString); x }
  def pt[t](x: t) = { println(x.toString); x }
  def pe[t](x: t) = { println("error! " + x.toString); x }

  val ptline = { () =>
    1 to 2 foreach (_ => print("\n"))
  }

  lazy val getHomeDir: String = System.getProperty("user.home")
  def getDateStr = new Date().toLocaleString
  def printdate() = pt(getDateStr)

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

/*
  def unzip41[a, b, c, d](
      xs: Seq[(a, b, c, d)]): (Seq[a], Seq[b], Seq[c], Seq[d]) = {
    val x1 = xs.map { case (a, b, c, d) => (a, b, (c, d)) }.unzip3
    val (a, b, (c, d)) = x1.copy(_3 = x1._3.unzip)
    (a, b, c, d)
  }
 */
