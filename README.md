#spray-geoip

A reactive, non-blocking GeoIp lookup rest service.

##Requirements

* [scala](http://scala-lang.org/)
* [sbt](http://www.scala-sbt.org/)
* [heroku](http://heroku.com) account (optional)
* [heroku toolbelt](https://devcenter.heroku.com/articles/getting-started-with-scala#set-up) (optional)

##Dependencies

This project runs powered by:

* [spray](http://spray.io)
* [akka](http://akka.io)
* [snowplow/scala-maxmind-iplookups](https://github.com/snowplow/scala-maxmind-iplookups)
* [MaxMind GeoLite data](http://maxmind.com)

##Install

After checking the project requirements, just clone the repo:

	$ git clone https://github.com/kpacha/spray-geoip.git
	$ cd spray-geoip

The project is ready to be deployed on heroku. You'll need the [heroku toolbelt](https://devcenter.heroku.com/articles/getting-started-with-scala#set-up) in order to use `foreman` or `heroku` commands.

##Run

###Local

Run the project using `sbt`

	$ sbt run

	...

	[info] Running GeoIPApp 
	Starting on port: 8080
	ipLookups instantion. Reading data from src/main/resources/GeoLiteCity.dat
	[INFO] [11/08/2014 22:23:06.123] [system-actor-akka.actor.default-dispatcher-4] [akka://system-actor/user/IO-HTTP/listener-0] Bound to /0.0.0.0:8080

and the service will we waiting for you on port 8080

###Foreman

Compile, package and launch the project

	$ sbt compile stage && foreman start web
	Loading /home/kpacha/scala/sbt-0.13.1/bin/sbt-launch-lib.bash
	[info] Loading global plugins from /home/kpacha/.sbt/plugins
	[info] Loading project definition from /home/kpacha/scala/eclipse/workspace/spray-geoip/project
	[info] Set current project to spray-geoip (in build file:/home/kpacha/scala/eclipse/workspace/spray-geoip/)
	[success] Total time: 2 s, completed 08-nov-2014 22:31:08
	[info] Packaging /home/kpacha/scala/eclipse/workspace/spray-geoip/target/scala-2.10/spray-geoip_2.10-0.0.1-SNAPSHOT-sources.jar ...
	[info] Wrote /home/kpacha/scala/eclipse/workspace/spray-geoip/target/scala-2.10/spray-geoip_2.10-0.0.1-SNAPSHOT.pom
	[info] Main Scala API documentation to /home/kpacha/scala/eclipse/workspace/spray-geoip/target/scala-2.10/api...
	[info] Done packaging.
	[info] Packaging /home/kpacha/scala/eclipse/workspace/spray-geoip/target/scala-2.10/spray-geoip_2.10-0.0.1-SNAPSHOT.jar ...
	[info] Done packaging.
	model contains 7 documentable templates
	[info] Main Scala API documentation successful.
	[info] Packaging /home/kpacha/scala/eclipse/workspace/spray-geoip/target/scala-2.10/spray-geoip_2.10-0.0.1-SNAPSHOT-javadoc.jar ...
	[info] Done packaging.
	[success] Total time: 31 s, completed 08-nov-2014 22:31:39
	22:31:42 web.1  | started with pid 18232
	22:31:45 web.1  | Starting on port: 5000
	22:31:45 web.1  | ipLookups instantion. Reading data from src/main/resources/GeoLiteCity.dat
	22:31:48 web.1  | [INFO] [11/08/2014 22:31:48.903] [system-actor-akka.actor.default-dispatcher-5] [akka://system-actor/user/IO-HTTP/listener-0] Bound to /0.0.0.0:5000

`foreman` launches the `spray-geoip` service no port 5000.

###Heroku

Read the (official doc)[https://devcenter.heroku.com/articles/getting-started-with-scala#introduction]

A live demo is available at http://damp-earth-2108.herokuapp.com/
