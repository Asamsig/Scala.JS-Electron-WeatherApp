enablePlugins(ScalaJSPlugin, ScalaJSBundlerPlugin, SbtJsonPlugin)

name := "Scalajselectron"
version := "0.1.0"

scalaVersion := "2.12.6"

jsonUrls += "https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20weather.forecast%20where%20woeid%20in%20(select%20woeid%20from%20geo.places(1)%20where%20text=%27Trondheim,%20NO%27)%20and%20u=%27c%27&format=json"
jsonInterpreter := plainCaseClasses.withPlayJsonFormats

// compiler flags
scalacOptions ++= Seq(
  "-P:scalajs:sjsDefinedByDefault",
  "-feature"
)

resolvers ++= Seq()

libraryDependencies ++= Seq(
  "org.scala-js"                      %%% "scalajs-dom" % "0.9.5",
  "com.typesafe.play"                 %%% "play-json"   % "2.6.7",
  "com.github.japgolly.scalajs-react" %%% "core"        % "1.2.0",
  "com.github.japgolly.scalajs-react" %%% "extra"       % "1.2.0"
)

npmDependencies in Compile ++= Seq(
  "react"     -> "16.2.0",
  "react-dom" -> "16.2.0"
)

webpackConfigFile := Some(baseDirectory.value / "webpack.config.js")

// optionally use yarn over npm
useYarn := false

// put all js dependencies into a single output file
skip in packageJSDependencies := true

// call the `main` method after the js is loaded
scalaJSUseMainModuleInitializer := true

// do not emit source maps in production
emitSourceMaps in fullOptJS := false

version in webpack := "4.8.1"

version in startWebpackDevServer := "3.1.4"

webpackDevServerExtraArgs := Seq("--inline")
