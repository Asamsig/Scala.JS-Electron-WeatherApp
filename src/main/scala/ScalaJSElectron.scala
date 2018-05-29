import WeatherApp.WeatherComponent
import org.scalajs.dom
import dom.document
import jsonmodels.yql.Yql
import org.scalajs.dom.ext.Ajax
import play.api.libs.json.Json

import scala.concurrent.ExecutionContext.Implicits.global

object ScalaJSElectron extends App {

//  document.getElementById("hello-world").innerHTML = s"""<div class="loader"></div>"""
//
//  Ajax
//    .get(
//      "https://query.yahooapis.com/v1/public/yql?q=" +
//        """select *
//          from weather.forecast where woeid in (select woeid from geo.places(1) where text='Trondheim, NO')
//          and u='c'&format=json""""
//    )
//    .map { xhr =>
//      val data = Json.parse(xhr.responseText).as[Yql]
//      val forecasts = data.query.results.channel.item.forecast.map {
//        forecastForDay =>
//          s"""
//            <p>
//              Date: ${forecastForDay.day} ${forecastForDay.date}<br/>
//              Weather: ${forecastForDay.low}-${forecastForDay.high}° ${forecastForDay.text}
//            </p><br/>
//            """
//      }.mkString
//      document.body.style.backgroundSize = "900px"
//      document.body.style.backgroundImage = "url('http://farm3.staticflickr.com/2598/3722008042_3a4bed59b7_o.jpg')"
//      document.getElementById("hello-world").innerHTML = s"""<div>
//      <h1>${data.query.results.channel.location.city}</h1>
//      $forecasts
//    </div>"""
//    }

  WeatherApp.WeatherComponent().renderIntoDOM(dom.document.getElementById("hello-world"))
}
