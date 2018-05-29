import japgolly.scalajs.react.extra.{Ajax, OnUnmount}
import jsonmodels.yql.{Forecast, Yql}
import org.scalajs.dom.document
import play.api.libs.json.Json

object WeatherApp {

  import japgolly.scalajs.react._
  import vdom.html_<^._

  sealed trait AsyncState
  case object InFlight extends AsyncState
  case class Failed(reason: String) extends AsyncState

  case class State(lastSuccess: Option[Yql], async: Option[AsyncState]) {
    val inFlight: Boolean =
      async.exists {
        case InFlight  => true
        case _: Failed => false
      }
  }

  private val ForecastList = ScalaFnComponent[Seq[Forecast]] { props =>
    def findAverageTemp(forecast: Forecast) =
      (forecast.low.toInt + forecast.high.toInt) / 2
    def createForecastItem(forecast: Forecast) =
      <.dt(
        <.div(^.cls := "card",
              <.h4(<.b(s"${forecast.day} ${forecast.date.dropRight(5)}")),
              <.p(s"${findAverageTemp(forecast)}° ${forecast.text}"))
      )

    <.dl(props map createForecastItem: _*)
  }

  class Backend($ : BackendScope[Unit, State]) extends OnUnmount{

    val ajax = Ajax
      .get(
        """https://query.yahooapis.com/v1/public/yql?q=select * from weather.forecast where woeid in (select woeid from geo.places(1) where text='Trondheim, NO') and u='c'&format=json""")
      .send
      .onComplete { xhr =>
        xhr.status match {
          case 200 =>
            $.setState(State(Json.parse(xhr.responseText).asOpt[Yql], None))
          case _ =>
            $.modState(
              _.copy(async = Some(Failed(Ajax.deriveErrorMessage(xhr)))))
        }
      }

    def render(s: State) = {


      val asyncStatus = s.async.whenDefined {
        case InFlight  => <.div(^.cls := "loader")
        case f: Failed => <.div(^.color.red, s"REQUEST FAILED.", <.br, f.reason)
      }

      val lastSuccess =
        s.lastSuccess.whenDefined { yql =>
          document.body.style.backgroundImage =
            "url('http://farm3.staticflickr.com/2598/3722008042_3a4bed59b7_o.jpg')"
          <.div(
            <.h1(^.cls := "city-headline",
                 yql.query.results.channel.location.city),
            ForecastList(yql.query.results.channel.item.forecast)
          )
        }
      <.div(lastSuccess, asyncStatus)

    }

  }

  val WeatherComponent = ScalaComponent
    .builder[Unit]("WeatherApp")
    .initialState(State(None, Some(InFlight)))
    .renderBackend[Backend]
    .componentDidMount(_.backend.ajax.asCallback)
    .build

}
