package com.doofin.stdScala

import java.io.PrintWriter;
import java.io.File;
import scala.concurrent.{Await, ExecutionContext, Future, duration}
import scala.concurrent.duration.FiniteDuration

object jvmUtils {
  def command(value: String) = sys.process.stringToProcess(value).!!

  def writeToFile(path: String, s: String) = {
    val pw = new PrintWriter(new File(path))
    pw.println(s)
    pw.close()
  }

  def readTextFile(path: String) = {
    val src = scala.io.Source.fromFile(path)
    val s = src.mkString
    src.close()
    s
  }
}

// def awaitFuture[t](x: Future[t]): t = Await result (x, duration.Duration.Inf)

// extension [t](x: Future[t]) { def await = awaitFuture(x) }

// val secondsDuration: Int => FiniteDuration = x => x.seconds
