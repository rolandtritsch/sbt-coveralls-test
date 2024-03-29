/*
  The projects test aggregation of coverage reports from two sub-projects.
  The sub-projects are in the directories partA and partB.
 */

import org.scoverage.coveralls.Imports.CoverallsKeys._

lazy val commonSettings = Seq(
  organization := "org.scoverage",
  version := "0.1.0",
  scalaVersion := "2.13.12",
  coverallsTokenFile := Some("./.coverallsToken")
)

def module(name: String) = {
  val id = s"part$name"
  Project(id = id, base = file(id))
    .settings(commonSettings: _*)
    .settings(
      Keys.name := name,
      libraryDependencies += "org.scalameta" %% "munit" % "0.7.29" % Test
    )
}

lazy val partA = module("A")
lazy val partB = module("B")

lazy val root = (project in file("."))
  .settings(commonSettings: _*)
  .settings(
    name := "root",
    test := {}
  )
  .aggregate(
    partA,
    partB
  )
