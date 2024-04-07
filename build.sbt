name := "scalaland"
version := "1.0"
scalaVersion := "2.12.18"

libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.18" % Test
libraryDependencies += "org.scalactic" %% "scalactic" % "3.2.18"

Compile / mainClass := Some("com.maly.scalaland.SimulatePlanApp")

//assembly / mainClass := Some("com.maly.scalaland.SimulatePlanApp")
//assembly / assemblyJarName := Some("retcalc.jar")
