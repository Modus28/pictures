name := "pictures"

version := "1.0"

scalaVersion := "2.11.8"

scalaSource in Compile := baseDirectory.value / "src/dbg28"

scalaSource in Test := baseDirectory.value / "src/dbg28/Test"

resolvers += Resolver.url("scoverage-bintray", url("https://dl.bintray.com/sksamuel/sbt-plugins/"))(Resolver.ivyStylePatterns)
coverageEnabled := true
coverageExcludedPackages := ".*Test*.*;.*package*.*"
libraryDependencies ++= Seq(
  "junit" % "junit" % "4.12",
  "com.novocode" % "junit-interface" % "0.11" % Test
        exclude("junit", "junit-dep")
)

