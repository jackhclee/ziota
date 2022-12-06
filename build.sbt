scalaVersion := "2.13.10"

val zioVer = "2.0.4"
libraryDependencies ++= Seq (
  "dev.zio"            %% "zio"               % zioVer,
  "dev.zio"            %% "zio-logging"       % "2.1.0",
  "org.postgresql"      % "postgresql"        % "42.5.0",
  "com.typesafe.slick" %% "slick"             % "3.4.1",
  "org.slf4j"           % "slf4j-nop"         % "1.7.26",
  "com.typesafe.slick" %% "slick-hikaricp"    % "3.4.1",
  "dev.zio"            %% "zio-test"          % zioVer % Test,
  "dev.zio"            %% "zio-test-sbt"      % zioVer % Test,
  "dev.zio"            %% "zio-test-magnolia" % zioVer % Test
)

testFrameworks += new TestFramework("zio.test.sbt.ZTestFramework")

