import org.scalajs.dom

object ScalaJSElectron extends App {

  WeatherApp.WeatherComponent().renderIntoDOM(dom.document.getElementById("hello-world"))

}
