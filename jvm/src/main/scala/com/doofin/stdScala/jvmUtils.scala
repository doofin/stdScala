package com.doofin.stdScala

// import org.scalatest.FunSuite

import java.io.PrintWriter;
import java.io.File;
import scala.concurrent.{Await, ExecutionContext, Future, duration}
import scala.concurrent.duration.FiniteDuration

trait jvmUtils {
  def command(value: String) = sys.process.stringToProcess(value).!!
  // val secondsDuration: Int => FiniteDuration = x => x.seconds
  def writeToFile(url: String, s: String) = {
    val pw = new PrintWriter(new File(url))
    pw.println(s)
    pw.close()
  }

  def readTextFile(value: String) = {
    val src = scala.io.Source.fromFile(value)
    val s = src.mkString
    src.close()
    s
  }

  def awaitFuture[t](x: Future[t]): t = Await result (x, duration.Duration.Inf)
}
