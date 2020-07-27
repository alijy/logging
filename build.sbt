val appName = "logging"

lazy val library = Project(appName, file("."))
  .enablePlugins(SbtAutoBuildPlugin, SbtGitVersioning, SbtArtifactory)
  .settings(
    majorVersion := 0,
    scalaVersion := "2.11.12",
    libraryDependencies ++= AppDependencies(),
    crossScalaVersions := Seq("2.11.12", "2.12.12"),
    parallelExecution in Test := false,
    makePublicallyAvailableOnBintray := true
  )
