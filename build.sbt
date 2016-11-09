name := "pictures"

version := "1.0"

scalaVersion := "2.11.8"

scalaSource in Compile := baseDirectory.value / "src"

scalaSource in Test := baseDirectory.value / "src/dbg28/Test"
fork := true
resolvers += Resolver.url("scoverage-bintray", url("https://dl.bintray.com/sksamuel/sbt-plugins/"))(Resolver.ivyStylePatterns)
coverageEnabled := true
coverageExcludedPackages := ".*Test*.*;.*ErrorManager*.*"
libraryDependencies ++= Seq(
  "junit" % "junit" % "4.12",
  "com.novocode" % "junit-interface" % "0.11" % Test
        exclude("junit", "junit-dep")
)

