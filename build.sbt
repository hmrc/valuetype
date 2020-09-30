import sbt.Keys._
import sbt._
import uk.gov.hmrc.DefaultBuildSettings._
import uk.gov.hmrc.SbtAutoBuildPlugin
import uk.gov.hmrc.versioning.SbtGitVersioning
import scoverage.ScoverageKeys

val appName = "valuetype"

lazy val scoverageSettings = Seq(
  ScoverageKeys.coverageExcludedPackages := "<empty>;.*BuildInfo.*",
  ScoverageKeys.coverageMinimum := 95,
  ScoverageKeys.coverageFailOnMinimum := false,
  ScoverageKeys.coverageHighlighting := true,
  ScoverageKeys.coverageEnabled := false
)

lazy val valuetype = Project(appName, file("."))
  .enablePlugins(SbtAutoBuildPlugin, SbtGitVersioning)
  .settings(scalaSettings ++ scoverageSettings: _*)
  .settings(
    targetJvm := "jvm-1.8",
    scalaVersion := "2.11.8",
    libraryDependencies ++= (compileDependencies ++ testDependencies),
    resolvers := Seq(
      Resolver.bintrayRepo("hmrc", "releases"), "typesafe-releases" at "http://repo.typesafe.com/typesafe/releases/"
    ),
    crossScalaVersions := Seq("2.11.5")
  )

  val compileDependencies = Seq(
    "com.typesafe.play" %% "play" % "2.6.24" % "provided",
    "com.typesafe.play" %% "play-json" % "2.6.14" % "provided"
  )

  val testDependencies = Seq(
    "org.scalacheck" %% "scalacheck" % "1.13.4" % "test",
    "org.scalatest" %% "scalatest" % "3.0.6" % "test",
    "org.pegdown" % "pegdown" % "1.6.0" % "test"
  )

