name := "urlShortener"

version := "0.0.1-SNAPSHOT"

scalaVersion := "2.10.4"

libraryDependencies ++= Seq(
  "net.debasishg" %% "redisclient" % "3.0",
  "com.twitter.finatra" %% "finatra-benchmarks" % "2.0.0.M2",
  "org.mockito" % "mockito-core" % "1.9.5" % "test",
  "org.scalatest" %% "scalatest" % "2.2.3" % "test",
  "org.specs2" %% "specs2" % "2.3.12" % "test",
  "com.twitter.finatra" %% "finatra-http" % "2.0.0.M2",
  "com.twitter.finatra" %% "finatra-logback" % "2.0.0.M2",
  "com.twitter.finatra" %% "finatra-httpclient" % "2.0.0.M2",
  "com.twitter.finatra" %% "finatra-utils" % "2.0.0.M2",
  "com.twitter.finatra" %% "finatra-jackson" % "2.0.0.M2",
  "ch.qos.logback" % "logback-classic" % "1.1.2"
)

resolvers ++= Seq(
  "Twitter Maven" at "http://maven.twttr.com",
  "Finatra Repo" at "http://twitter.github.com/finatra"
)
