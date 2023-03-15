package com.doofin.stdScala

import scala.util.*

import java.util.Date
import java.util.concurrent.TimeUnit

import scala.concurrent.{Await, ExecutionContext, Future, duration}
import scala.concurrent.duration.Duration

/** print utils,etc */
trait miscUtils {

  /** throw new RuntimeException if block fails */
  def throwE[T](block: => T, msg: String = ""): T = {
    Try(block) match {
      case Failure(exception) =>
        throw new RuntimeException(
          s"${if msg.isEmpty() then "" else msg + ", "}${exception.getMessage}"
        )
      case Success(value) => value
    }

  }

  def time[R](block: => R): R = {
    val t0 = System.nanoTime()
    val result = block // call-by-name
    val t1 = System.nanoTime()
    val ns: Long = t1 - t0
    val s: Long = TimeUnit.SECONDS.convert(ns, TimeUnit.NANOSECONDS)
    println(s"Elapsed time: ${ns.toString} ns , ${s} s")
    result
  }

  extension [t](f: Future[t]) {

    /** awaitFuture */
    def unwarp = Await.result(f, Duration.Inf)
  }
}
