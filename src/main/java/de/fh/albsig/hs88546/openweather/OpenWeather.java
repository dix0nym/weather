package de.fh.albsig.hs88546.openweather;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * OpenWeather API.
 *
 * @author Dominic Schaa
 *
 */
public class OpenWeather {
  private static Logger logger = LogManager.getLogger(OpenWeather.class);

  private String apiKey = "d380ea7a6cbc8de8181f9cdc96df5982";

  private final String baseurl = "https://api.openweathermap.org/data/2.5/weather";

  public OpenWeather() {
  }

  public OpenWeather(String apiKey) {
    logger.info("setting apiKey to {}", apiKey);
    this.apiKey = apiKey;
  }

  /**
   * Returns Weather by cityId see http://bulk.openweathermap.org/sample/
   *
   * @param cityId id of City
   * @return Weather
   */
  public Weather getByCityId(int cityId) {
    logger.info("requested weather by cityId: {}", cityId);
    final String url = String.format("%s?id=%d&appid=%s&units=metric", this.baseurl, cityId,
        this.apiKey);
    final JSONObject jobj = this.performRequest(url);
    if (jobj == null) {
      return null;
    }
    return this.parseJsonResponse(jobj);
  }

  /**
   * Returns Weather by city name.
   *
   * @param city name of the city
   * @return Weather
   */
  public Weather getByCityName(String city) {
    logger.info("requested weather by name: {}", city);
    final String url = String.format("%s?q=%s&appid=%s&units=metric", this.baseurl, city,
        this.apiKey);
    final JSONObject jobj = this.performRequest(url);
    if (jobj == null) {
      return null;
    }
    return this.parseJsonResponse(jobj);
  }

  /**
   * Returns Weather by Coordinates.
   *
   * @param lat Latitude
   * @param lon Longitude
   * @return Weather
   */
  public Weather getByCoords(int lat, int lon) {
    logger.info("requested weather by coordinates: {}|{}", lat, lon);
    final String url = String.format("%s?lat=%d&lon=%d&appid=%s&units=metric", this.baseurl, lat,
        lon, this.apiKey);
    final JSONObject jobj = this.performRequest(url);
    if (jobj == null) {
      return null;
    }
    return this.parseJsonResponse(jobj);
  }

  /**
   * Returns Weather by ZIP-Code and Country.
   *
   * @param code    zip-code
   * @param country zip in country
   * @return Weather
   */
  public Weather getByZip(int code, String country) {
    logger.info("requested weather by zip: {} in {}", code, country);
    final String url = String.format("%s?zip=code,country&appid=%&units=metrics", this.baseurl,
        code, country, this.apiKey);
    final JSONObject jobj = this.performRequest(url);
    if (jobj == null) {
      return null;
    }
    return this.parseJsonResponse(jobj);
  }

  private double getDouble(JSONObject jobj, String key) {
    return ((Number) jobj.get(key)).doubleValue();
  }

  /**
   * Tries to parse JSONObject to Weather.
   *
   * @param jobj JObject to parse
   * @return Weather
   */
  private Weather parseJsonResponse(JSONObject jobj) {
    logger.info("parsing json response to weather obj");
    try {
      final Weather weather = new Weather();

      final String city = jobj.get("name").toString();
      weather.setCity(city);

      final JSONObject coords = (JSONObject) jobj.get("coord");
      weather.setLon(this.getDouble(coords, "lon"));
      weather.setLat(this.getDouble(coords, "lat"));

      final JSONObject main = (JSONObject) jobj.get("main");
      weather.setTemp(this.getDouble(main, "temp"));
      weather.setTempMin(this.getDouble(main, "temp_min"));
      weather.setTempMax(this.getDouble(main, "temp_max"));
      weather.setPressure((long) main.get("pressure"));
      weather.setHumidity((long) main.get("humidity"));

      final JSONObject wind = (JSONObject) jobj.get("wind");
      weather.setWind_speed(this.getDouble(wind, "speed"));
      weather.setWind_deg((long) wind.get("deg"));

      final JSONArray weatherArray = (JSONArray) jobj.get("weather");
      final JSONObject wObj = (JSONObject) weatherArray.get(0);
      weather.setDescription(wObj.get("main").toString());
      weather.setFullDescription(wObj.get("description").toString());

      return weather;
    } catch (final NullPointerException e) {
      logger.error("failed to parse weather obj from json: {} {}", e.getMessage(), e);
    }
    return null;
  }

  /**
   * Performs HTTP-Get request at URL and tries to parse response as JSON.
   *
   * @param url url to request
   * @return JSONObject
   */
  private JSONObject performRequest(String url) {
    logger.info("requesting Weather data");
    try {
      final URL urlObj = new URL(url);
      final HttpURLConnection conn = (HttpURLConnection) urlObj.openConnection();
      conn.setRequestMethod("GET");
      conn.connect();

      final int status_code = conn.getResponseCode();

      if (status_code != 200) {
        // error handling
        logger.error("failed to perform API-Request: {}", status_code);
        return null;
      }
      final StringBuilder response = new StringBuilder();
      try (BufferedReader br = new BufferedReader(
          new InputStreamReader(conn.getInputStream(), "utf-8"))) {
        String responseLine = null;
        while ((responseLine = br.readLine()) != null) {
          response.append(responseLine.trim());
        }
      }
      final JSONParser jparser = new JSONParser();
      final JSONObject jobj = (JSONObject) jparser.parse(response.toString());
      return jobj;
    } catch (final MalformedURLException e) {
      logger.error("failed to generate valid url: {} {}", e.getMessage(), e);
    } catch (final IOException e) {
      logger.error("failed to perform API-Request: {} {}", e.getMessage(), e);
    } catch (final ParseException e) {
      logger.error("failed to parse response: invalid json: {} {}", e.getMessage(), e);
    }
    return null;
  }

}
