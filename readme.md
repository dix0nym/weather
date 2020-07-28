# Weather-App

Kleine Spring-Boot-Applikation, die das aktuelle Wetter durch die [API von OpenWeatherMap](https://openweathermap.org/api) im Format JSON bezieht. Die erhaltenen Wetterdaten werden aufbereitet und im Format XML dargestellt. Verschiedene implementierte Endpunkte erlauben es, das Wetter nach Stadtname, Id der Stadt, Längen -und Breitengrade oder nach Postleitzahl zu suchen.

## Installation

```bash
$ git clone https://github.com/dix0nym/weather.git
$ cd weather
$ mvn clean install
```

## Usage

```bash
$ mvn spring-boot:run
```

im Browser: [http://127.0.0.1:8080/weather/city/albstadt](http://127.0.0.1:8080/weather/city/albstadt)

## REST-Endpoints

* `/weather/city/{city}`
  * STRING `city` - Name der Stadt - z. B. Albstadt
  * Beispiel: [/weather/city/albstadt](http://127.0.0.1:8080/weather/city/albstadt)

* `/weather/id/{id}`
  * INT `id` - Id der Stadt z. B. 2958595
  * vollständige List in [city.list.json.gz](http://bulk.openweathermap.org/sample/) von OpenWeatherMap
  * Beispiel: [/weather/id/2958595](http://127.0.0.1:8080/weather/id/2958595)

* `/weather/coords/{lat}/{lon}`
  * DOUBLE `lat` - Latitude
  * DOUBLE `lon` - Longitude
  * Beispiel: [weather/coords/48.22/9.03](http://127.0.0.1:8080/weather/coords/48.22/9.03)

* `/weather/zip/{code}/{country}`
  * INT `code` = Postleitzahl - z. B. 72458
  * STRING `country` = [Ländercode](https://en.wikipedia.org/wiki/ISO_3166-1_alpha-2) z. B. de, us, it
  * Beispiel: [/weather/zip/72458/de](http://127.0.0.1:8080/weather/zip/72458/de)

## Tools

* checkstyles: `mvn checkstyle:checkstyle`
* spotbugs
  * `mvn com.github.spotbugs:spotbugs-maven-plugin:4.0.4:spotbugs`
  * `mvn com.github.spotbugs:spotbugs-maven-plugin:4.0.4:gui`
