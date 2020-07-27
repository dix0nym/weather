# Weather-App

## Installation

```
$ git clone https://github.com/dix0nym/weather.git
$ cd weather
$ mvn clean install
```

## Usage

```
$ mvn spring-boot:run
```

im Browser: [http://127.0.0.1:8080/weather/city/albstadt](http://127.0.0.1:8080/weather/city/albstadt)

## API-Endpoints

* `/weather/city/{city}`
  * STRING `city` - Name der Stadt - z. B. Albstadt
  * Beispiel: `/weather/city/albstadt`

* `/weather/id/{id}`
  * INT `id` - Id der Stadt z. B. 2958595
  * vollständige List in [city.list.json.gz](http://bulk.openweathermap.org/sample/) von OpenWeatherMap
  * Beispiel: `/weather/id/2958595`

* `/weather/coords/{lat}/{lon}`
  * DOUBLE `lat` - Latitude
  * DOUBLE `lon` - Longitude
  * Beispiel: `weather/coords/9/48`

* `/weather/zip/{code}/{country}`
  * INT `code` = Postleitzahl - z. B. 72458
  * STRING `country` = [Ländercode](https://en.wikipedia.org/wiki/ISO_3166-1_alpha-2) z. B. de, us, it
  * Beispiel: `/weather/zip/72458/de`

## Tools

* checkstyles: `mvn checkstyle:checkstyle`
* spotbugs
  * `mvn spotbugs:spotbugs`
  * `mvn spotbugs:gui`