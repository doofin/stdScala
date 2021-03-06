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
  val supportedScalaVersions = List("2.12.12", "2.13.3")
  val mScalaVersion = supportedScalaVersions(0)

  val cmSettings =
    Seq(
      name := "stdscala",
      organization := "com.doofin",
      version := "0.1-SNAPSHOT"
    )

  lazy val sharedPure = (crossProject(JSPlatform, JVMPlatform)
    .crossType(CrossType.Pure) in file("shared"))
    .settings(
      cmSettings,
      resolvers ++= Seq("jitpack" at "https://jitpack.io"),
      crossScalaVersions := supportedScalaVersions,
      scalaVersion := mScalaVersion,
      libraryDependencies ++= deps.sharedDeps.value
    )

  lazy val js: Project = (project in file("js"))
    .dependsOn(sharedPure.js)
    .enablePlugins(ScalaJSPlugin, ScalaJSBundlerPlugin) //ScalaJSWeb is for sourcemap
    .settings(
      cmSettings,
      scalacOptions ++= mScalacOptions,
      libraryDependencies ++= deps.jsDeps.value,
      webpackBundlingMode := BundlingMode.LibraryAndApplication(),
      scalaJSUseMainModuleInitializer := true,
      scalaJSLinkerConfig := StandardConfig()
        .withSourceMap(false)
        .withModuleKind(ModuleKind.CommonJSModule),
//      https://github.com/scala-js/scala-js/issues/4305
      scalaJSLinkerConfig ~= { _.withESFeatures(_.withAvoidClasses(false)) }
    )

  lazy val jvm = (project in file("jvm"))
    .dependsOn(sharedPure.jvm)
    .enablePlugins(JavaAppPackaging, UniversalPlugin)
    .settings(
      cmSettings,
      crossScalaVersions := supportedScalaVersions,
      libraryDependencies ++= (deps.jvmDeps
        ++ (CrossVersion
          .partialVersion(scalaVersion.value) match {
          case Some((2, major)) if major <= 12 =>
            Seq()
          case _ =>
            Seq(
              "org.scala-lang.modules" %% "scala-parallel-collections" % "1.0.0"
            )
        }))
    )
}
