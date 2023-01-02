val shared = build.sharedPure
//val js = build.js
//val jvm = build.jvm

addCommandAlias("p", "+publishLocal")

scalacOptions ++= {
  Seq(
    "-encoding",
    "UTF-8",
    "-feature",
    "-language:implicitConversions"
    // disabled during the migration
    // "-Xfatal-warnings"
  ) ++
    (CrossVersion.partialVersion(scalaVersion.value) match {
      case Some((3, _)) =>
        Seq(
          "-unchecked",
          "-source:3.0-migration"
        )
      case _ =>
        Seq(
          "-deprecation",
          "-Xfatal-warnings",
          "-Wunused:imports,privates,locals",
          "-Wvalue-discard"
        )
    })
}
