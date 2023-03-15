package com.doofin.stdScala

import java.util.Date
import java.util.concurrent.TimeUnit
import scala.util.{Failure, Success, Try}

/** system environments like date, current time, etc */
object systemEnvir {
  def getHomeDir: String = System.getProperty("user.home")
  def getCurrDir: String = System.getProperty("user.dir")

  def currTime = System.currentTimeMillis()
  def getDateStr = new Date().toLocaleString
//   def printdate = lg(getDateStr)
}
