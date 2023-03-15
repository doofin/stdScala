import org.scalajs.linker.interface.StandardConfig
import sbt._
import sbt.Keys._
import sbt.internal.io.Source
// sjs
import org.scalajs.sbtplugin.ScalaJSPlugin
import org.scalajs.sbtplugin.ScalaJSPlugin.autoImport._
//sjs bundler
import scalajsbundler.sbtplugin.ScalaJSBundlerPlugin.autoImport._
import scalajsbundler.sbtplugin.ScalaJSBundlerPlugin
//cross
import org.portablescala.sbtplatformdeps.PlatformDepsPlugin.autoImport._
import sbtcrossproject.CrossPlugin.autoImport.{CrossType, crossProject, _}
import scalajscrossproject.ScalaJSCrossPlugin.autoImport._
//packager
import com.typesafe.sbt.packager.archetypes.JavaAppPackaging
import com.typesafe.sbt.packager.universal.UniversalPlugin

object build {
  val mScalacOptions = Seq()
  //    Seq("-Xcheckinit", "-language:postfixOps", "-Xmigration", "-deprecation")
  // val supportedScalaVersions = List("2.12.12", "2.13.10", "3.2.1")
  val mScalaVersion = "3.2.1" //dbg only works for scala 3

  val cmSettings =
    Seq(
      name := "stdscala",
      organization := "com.doofin",
      version := "3-SNAPSHOT" //for scala3
    )

  lazy val sharedPure = (crossProject(JSPlatform, JVMPlatform)
    .crossType(CrossType.Full)
    .jvmConfigure(_.enablePlugins(JavaAppPackaging, UniversalPlugin))
    .jsConfigure(
      _.enablePlugins(
        ScalaJSPlugin,
        ScalaJSBundlerPlugin
      )
    ) in file("."))
    .settings(
      cmSettings,
      resolvers ++= Seq("jitpack" at "https://jitpack.io"),
      // crossScalaVersions := supportedScalaVersions,
      scalaVersion := mScalaVersion,
      libraryDependencies ++= deps.sharedDeps.value
    )
    .jvmSettings(
      cmSettings,
      libraryDependencies ++= deps.jvmDeps
    )
    .jsSettings(
      cmSettings,
      libraryDependencies ++= deps.jsDeps.value,
      webpackBundlingMode := BundlingMode.LibraryAndApplication(),
      scalaJSUseMainModuleInitializer := true,
      scalaJSLinkerConfig := StandardConfig()
        .withSourceMap(false)
        .withModuleKind(ModuleKind.CommonJSModule),
//      https://github.com/scala-js/scala-js/issues/4305
      scalaJSLinkerConfig ~= { _.withESFeatures(_.withAvoidClasses(false)) }
    )

}
