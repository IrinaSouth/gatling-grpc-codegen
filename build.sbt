enablePlugins(GatlingPlugin)

scalaVersion := "2.12.7"

scalacOptions := Seq(
  "-encoding", "UTF-8", "-target:jvm-1.8", "-deprecation",
  "-feature", "-unchecked", "-language:implicitConversions", "-language:postfixOps")

libraryDependencies += "io.gatling.highcharts" % "gatling-charts-highcharts" % "3.0.0" % "test,it"
libraryDependencies += "io.gatling"            % "gatling-test-framework"    % "3.0.0" % "test,it"
libraryDependencies ++= Seq(
  "com.github.phisgr" %% "gatling-grpc" % "0.1.0" % "test,it"
)

libraryDependencies ++= Seq(
  "io.grpc" % "grpc-netty" % scalapb.compiler.Version.grpcJavaVersion,
  "com.thesamet.scalapb" %% "scalapb-runtime-grpc" % scalapb.compiler.Version.scalapbVersion
)

PB.targets in Compile := Seq(
  scalapb.gen() -> (sourceManaged in Compile).value
)