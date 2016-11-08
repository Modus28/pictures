name := "pictures"

version := "1.0"

scalaVersion := "2.11.8"

scalaSource in Compile := baseDirectory.value / "src/dbg28"

scalaSource in Test := baseDirectory.value / "src/dbg28/Test"
libraryDependencies ++= Seq(
  "junit" % "junit" % "4.12" % "test",
  "com.novocode" % "junit-interface" % "0.11" % Test
        exclude("junit", "junit-dep")
)

