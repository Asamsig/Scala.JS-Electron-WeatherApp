# ScalaJS Electron WeatherApp
This is a simple electron app that fetches weather data from Yahoo and displays it.

## Setup

Download an install the following applications:

* [SBT][sbt]
* [NodeJS][nodejs]
* [Yarn][yarn] (optional)

Next, run [SBT][sbt] on the command line to initialize a new sekeleton.

Install all the [NodeJS][nodejs] dependencies with [Yarn][yarn] (or [npm][npm]).

```
$ [npm|yarn] install
```

Next, launch [SBT][sbt] and build the sources

```
sbt> fastOptJS::webpack
```

At this point, you application's compiled `.js` file has been built and resides in the `web/js` folder along with a `.js.map` file and any other bundled, javascript dependencies.

To run your app

* Run via [Electron][electron]

If you are using [IntelliJ][intellij], there are already launch configurations created for each: Run/Debug ([Electron][electron]).

Otherwise, you can manually run/debug via the command line:

```
$ ./node_modules/.bin/electron main.js [debug]
```

If you pass the `debug` argument on the command line, then the webkit debugger will be opened as well. From there, you should have access to your [Scala][scala] code, as a map file was generated for it.

That's it!

## Structure

Here is a high-level breakdown of the source files:

* **project/** contains [SBT][sbt] plugins and settings
* **src/** contains all the [ScalaJS][scalajs] app code
* **web/** contains all the static resources, and your app bundle
* **main.js** is the [Electron][electron] application code

Everything in **web/** will be exposed to your application at runtime. And the [ScalaJS][scalajs] app will be compiled to JavaScript to **web/js/** folder.

## Credits
Based on [massungs template](https://github.com/massung/scala-js-skeleton.g8)

[scala]:        http://www.scala.org
[scalajs]:      http://www.scala-js.org
[bundler]:      https://scalacenter.github.io/scalajs-bundler
[webpack]:      https://webpack.github.io
[nodejs]:       https://nodejs.org
[electron]:     https://electron.atom.io
[yarn]:         https://yarnpkg.com/en
[npm]:          https://www.npmjs.com
[sbt]:          http://www.scala-sbt.org
[intellij]:     https://www.jetbrains.com/idea/
