name := """6441_Project"""
organization := "com.example"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.13.8"

libraryDependencies ++= Seq(guice, javaWs, ehcache,ws)

libraryDependencies += "org.apache.commons" % "commons-lang3" % "3.11"
libraryDependencies += "org.mockito" % "mockito-core" % "2.22.0" % "test"

javaOptions in Test ++= Seq(
  "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=9998",
  "-Xms512M",
  "-Xmx1536M",
  "-Xss1M",
  "-XX:MaxPermSize=384M"
)