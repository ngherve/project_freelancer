name := """play-java-starter-example"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.12.4"

crossScalaVersions := Seq("2.11.12", "2.12.4")

libraryDependencies ++= Seq(guice,javaWs)

libraryDependencies ++= Seq( "com.github.ben-manes.caffeine" % "caffeine" % "2.4.0")
libraryDependencies ++= Seq(
  ehcache
)
libraryDependencies ++= Seq(
  cacheApi
)

// Test Database
libraryDependencies += "com.h2database" % "h2" % "1.4.196"

// Testing libraries for dealing with CompletionStage...
libraryDependencies += "org.assertj" % "assertj-core" % "3.6.2" % Test
libraryDependencies += "org.awaitility" % "awaitility" % "2.0.0" % Test

// Make verbose tests
testOptions in Test := Seq(Tests.Argument(TestFrameworks.JUnit, "-a", "-v"))

// Javadoc
sources in (Compile, doc) ~= (_ filter (_.getName endsWith ".java"))

libraryDependencies += "org.apache.commons" % "commons-lang3" % "3.7"
// https://mvnrepository.com/artifact/javax.xml.bind/jaxb-api
libraryDependencies += "javax.xml.bind" % "jaxb-api" % "2.3.1"

javaOptions ++= Seq("<your runtime args>", "-Djdk.lang.Process.allowAmbiguousCommands=true")

libraryDependencies += "org.mockito" % "mockito-core" % "2.22.0" % "test"
libraryDependencies += "com.typesafe.akka" %% "akka-testkit" % "2.6.14" % Test
val AkkaVersion = "2.6.8"
val AkkaHttpVersion = "10.2.9"
libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor-typed" % AkkaVersion,
  "com.typesafe.akka" %% "akka-stream" % AkkaVersion,
  "com.typesafe.akka" %% "akka-http" % AkkaHttpVersion
)
libraryDependencies += "com.typesafe.akka" %% "akka-http-jackson" % AkkaHttpVersion
javaOptions ++= Seq("-Djdk.lang.Process.allowAmbiguousCommands=true")


//test
javaOptions in Test ++= Seq(
  "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=9998",
  "-Xms512M",
  "-Xmx1536M",
  "-Xss1M",
  "-XX:MaxPermSize=384M"
)