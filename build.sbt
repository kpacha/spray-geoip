import com.typesafe.sbt.SbtNativePackager._
import NativePackagerKeys._

packageArchetype.java_application

name := """spray-geoip"""

version := "0.0.1-SNAPSHOT"

scalaVersion := "2.10.3"

libraryDependencies ++={
  val akkaV = "2.3.6"
  val sprayV = "1.3.2"
  Seq(
    "io.spray"            %%  "spray-can"     % sprayV,
    "io.spray"            %%  "spray-caching" % sprayV,
    "io.spray"            %%  "spray-routing" % sprayV,
    "io.spray"            %%  "spray-json"    % "1.3.1",
    "com.typesafe.akka"   %%  "akka-actor"    % akkaV,
    "com.snowplowanalytics"  %% "scala-maxmind-iplookups"  % "0.2.0"
  )
}

resolvers ++= Seq(
  "Twitter" at "http://maven.twttr.com",
  "SnowPlow Repo" at "http://maven.snplow.com/releases/",
  "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"
)
