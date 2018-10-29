/*
 * Copyright 2015 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import sbt.Keys._
import sbt._
import uk.gov.hmrc.versioning.SbtGitVersioning.autoImport.majorVersion
import uk.gov.hmrc.SbtAutoBuildPlugin
import uk.gov.hmrc.versioning.SbtGitVersioning
import uk.gov.hmrc.SbtArtifactory
import uk.gov.hmrc.SbtArtifactory.autoImport.makePublicallyAvailableOnBintray

object HmrcBuild extends Build {

  import uk.gov.hmrc._

  val appName = "logging"

  lazy val library = Project(appName, file("."))
    .enablePlugins(SbtAutoBuildPlugin, SbtGitVersioning, SbtArtifactory)
    .settings(
      majorVersion := 0,
      scalaVersion := "2.11.8",
      libraryDependencies ++= AppDependencies(),
      crossScalaVersions := Seq("2.11.8"),
      parallelExecution in Test := false,
      makePublicallyAvailableOnBintray := true
    )
}

private object AppDependencies {

  val compile = Seq(
    "org.slf4j" % "slf4j-api" % "1.7.21" withSources()
  )

  trait TestDependencies {
    lazy val scope: String = "test"
    lazy val test: Seq[ModuleID] = ???
  }

  object Test {
    def apply() = new TestDependencies {
      override lazy val test = Seq(
        "org.scalatest" %% "scalatest" % "2.2.6" % scope,
        "org.pegdown" % "pegdown" % "1.4.2" % scope,
        "ch.qos.logback" % "logback-core" % "1.1.7" % scope,
        "ch.qos.logback" % "logback-classic" % "1.1.7" % scope
      )
    }.test
  }

  def apply() = compile ++ Test()
}
