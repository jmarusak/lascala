name := "scalaland"
version := "1.0"
scalaVersion := "2.12.18"

libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.18" % Test
libraryDependencies += "org.scalactic" %% "scalactic" % "3.2.18"

Compile / compile / scalacOptions ++= Seq("-deprecation")
Compile / doc / scalacOptions ++= Seq("-siteroot", ".")

Compile / mainClass := Some("retirement.SimulatePlanApp")

assembly / mainClass := Some("retirement.SimulatePlanApp")
assembly / assemblyJarName := "retirement.jar"
