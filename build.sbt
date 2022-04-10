name := """play-java-starter-example"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)


libraryDependencies ++= Seq(guice,javaWs,ehcache,cacheApi)

libraryDependencies ++= Seq( "com.github.ben-manes.caffeine" % "caffeine" % "2.4.0")


// Testing libraries for dealing with CompletionStage...
libraryDependencies += "org.assertj" % "assertj-core" % "3.6.2" % Test
libraryDependencies += "org.awaitility" % "awaitility" % "2.0.0" % Test


libraryDependencies += "org.apache.commons" % "commons-lang3" % "3.7"

// https://mvnrepository.com/artifact/javax.xml.bind/jaxb-api
libraryDependencies += "javax.xml.bind" % "jaxb-api" % "2.3.1"


libraryDependencies += "org.jacoco"  % "org.jacoco.agent"  %  "0.8.2" % "test"
libraryDependencies += "org.mockito" % "mockito-core" % "2.22.0" % "test"
libraryDependencies += "com.typesafe.akka" %% "akka-testkit" % "2.6.8" % Test


libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor-typed" % "2.6.8",
  "com.typesafe.akka" %% "akka-stream" % "2.6.8",
  "com.typesafe.akka" %% "akka-http" % "10.0.11"
)
dependencyOverrides += "org.scala-lang" % "scala-library" % "2.12.3"
libraryDependencies += "com.typesafe.akka" %% "akka-http-jackson" % "10.0.11"
javaOptions ++= Seq("-Djdk.lang.Process.allowAmbiguousCommands=true")


//test
javaOptions in Test ++= Seq(
  "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=9998",
  "-Xms512M",
  "-Xmx1536M",
  "-Xss1M",
  "-XX:MaxPermSize=384M",
)