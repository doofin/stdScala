val shared = build.sharedPure
val js = build.js
val jvm = build.jvm

/*
lazy val root = (project in file("."))
  .aggregate(js, jvm)
  .settings(
    // crossScalaVersions must be set to Nil on the aggregating project
    crossScalaVersions := Nil
//    publish / skip := true
  )
*/
