package com.doofin.stdScala

import org.scalatest.FunSuite

import java.io.PrintWriter;
import java.io.File;

trait jvmUtils {
  def writeToFile(url: String, s: String) = {
    val pw = new PrintWriter(new File(url))
    pw.println(s)
    pw.close()
  }
}
