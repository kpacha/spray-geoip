import akka.actor.Actor
import spray.routing._
import spray.http._
import MediaTypes._

class GeoIPServiceActor extends Actor with GeoIPService {
  def actorRefFactory = context
  def receive = runRoute(myRoute)
}

import spray.json._
import spray.json.DefaultJsonProtocol
import com.snowplowanalytics.maxmind.iplookups.IpLocation

object IpLocationJsonProtocol extends DefaultJsonProtocol {
  implicit val ipLocationFormat = jsonFormat12(IpLocation.apply)
}

import IpLocationJsonProtocol._
import com.snowplowanalytics.maxmind.iplookups.IpLookups
import scala.concurrent.duration.Duration
import spray.routing.directives.CachingDirectives._

trait GeoIPService extends HttpService {

  val ipLookups = {
    println("ipLookups instantion. Reading data from /tmp/GeoLiteCity.dat")
    IpLookups(geoFile = Some("GeoLiteCity.dat"), ispFile = None,
              orgFile = None, domainFile = None, memCache = true, lruCache = 20000)
  }

  val simpleCache = routeCache(maxCapacity = 1000, timeToIdle = Duration("30 min"))

  val myRoute =
    path("geoip" / """\b(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\b""".r) { ip =>
      get {
        cache(simpleCache) {
          respondWithMediaType(`application/json`) {
            complete {
              ipLookups.performLookups(ip)._1 match {
                case Some(ipLocation) => ipLocation.toJson.toString
                case _ => "null"
              }
            }
          }
        }
      }
    }
}

import akka.actor.{ActorSystem, Props}
import akka.io.IO
import spray.can.Http
import akka.pattern.ask
import akka.util.Timeout
import scala.concurrent.duration._
import util.Properties

object GeoIPApp extends App {
  implicit val system = ActorSystem("system-actor")
  val service = system.actorOf(Props[GeoIPServiceActor], "geoip-service")
  implicit val timeout = Timeout(5.seconds)

  val host = "0.0.0.0" 
  // val port = Option(System.getenv("PORT")).getOrElse("8080").toInt
  val port = Properties.envOrElse("PORT", "8080").toInt
  println("Starting on port: "+port)

  IO(Http) ? Http.Bind(service, interface = host, port = port)
}