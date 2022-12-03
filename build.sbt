scalaVersion := "2.13.10"

val zioVer = "2.0.4"
libraryDependencies ++= Seq (
  "dev.zio" %% "zio"               % zioVer,
  "dev.zio" %% "zio-test"          % zioVer % Test,
  "dev.zio" %% "zio-test-sbt"      % zioVer % Test,
  "dev.zio" %% "zio-test-magnolia" % zioVer % Test
)

testFrameworks += new TestFramework("zio.test.sbt.ZTestFramework")

