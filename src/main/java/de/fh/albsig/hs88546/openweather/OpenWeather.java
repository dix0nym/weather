package de.fh.albsig.hs88546.openweather;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

  private final OpenWeatherParser parser;

  public OpenWeather() {
    this.parser = new OpenWeatherParser();
  }

  /**
   * Overloaded OpenWeather Constructor to pass a API key.
   *
   * @param apiKey API-Key to use in requests
   */
  public OpenWeather(String apiKey) {
    logger.info("setting apiKey to {}", apiKey);
    this.apiKey = apiKey;
    this.parser = new OpenWeatherParser();
  }

  /**
   * Returns Weather by cityId see http://bulk.openweathermap.org/sample/
   *
   * @param cityId id of City
   * @return Weather
   */
  public Weather getByCityId(int cityId) {
    logger.info("requested weather by cityId: {}", cityId);
    URIBuilder builder = new URIBuilder();
    builder.setScheme("https").setHost("api.openweathermap.org").setPath("/data/2.5/weather")
        .addParameter("id", String.valueOf(cityId)).addParameter("appid", this.apiKey)
        .addParameter("units", "metric");
    String url = builder.toString();
    final JSONObject jobj = this.performRequest(url);
    if (jobj == null) {
      return null;
    }
    return this.parser.parseJsonResponse(jobj);
  }

  /**
   * Returns Weather by city name.
   *
   * @param city name of the city
   * @return Weather
   */
  public Weather getByCityName(String city) {
    logger.info("requested weather by name: {}", city);
    URIBuilder builder = new URIBuilder();
    builder.setScheme("https").setHost("api.openweathermap.org").setPath("/data/2.5/weather")
        .addParameter("q", city).addParameter("appid", this.apiKey).addParameter("units", "metric");
    String url = builder.toString();
    final JSONObject jobj = this.performRequest(url);
    if (jobj == null) {
      return null;
    }
    return this.parser.parseJsonResponse(jobj);
  }

  /**
   * Returns Weather by Coordinates.
   *
   * @param lat Latitude
   * @param lon Longitude
   * @return Weather
   */
  public Weather getByCoords(double lat, double lon) {
    logger.info("requested weather by coordinates: {}|{}", lat, lon);
    URIBuilder builder = new URIBuilder();
    builder.setScheme("https").setHost("api.openweathermap.org").setPath("/data/2.5/weather")
        .addParameter("lat", String.valueOf(lat)).addParameter("lon", String.valueOf(lon))
        .addParameter("appid", this.apiKey).addParameter("units", "metric");
    String url = builder.toString();
    final JSONObject jobj = this.performRequest(url);
    if (jobj == null) {
      return null;
    }
    return this.parser.parseJsonResponse(jobj);
  }

  /**
   * Returns Weather by ZIP-Code and Country.
   *
   * @param code    ZIP-code
   * @param country ZIP in country
   * @return Weather
   */
  public Weather getByZip(int code, String country) {
    logger.info("requested weather by zip: {} in {}", code, country);
    URIBuilder builder = new URIBuilder();
    builder.setScheme("https").setHost("api.openweathermap.org").setPath("/data/2.5/weather")
        .addParameter("zip", String.format("%d,%s", code, country))
        .addParameter("appid", this.apiKey).addParameter("units", "metric");
    String url = builder.toString();
    final JSONObject jobj = this.performRequest(url);
    if (jobj == null) {
      return null;
    }
    return this.parser.parseJsonResponse(jobj);
  }

  /**
   * Performs HTTP-Get request at URL and tries to parse response as JSON.
   *
   * @param url requested URL
   * @return JSONObject
   */
  public JSONObject performRequest(String url) {
    logger.info("requesting Weather data");
    try {
      CloseableHttpClient httpclient = HttpClients.createDefault();
      HttpGet httpGet = new HttpGet(url);
      CloseableHttpResponse response = httpclient.execute(httpGet);
      String body = null;
      try {
        int statusCode = response.getStatusLine().getStatusCode();
        if (statusCode != 200) {
          logger.error("failed to perform API-Request: {}", statusCode);
          return null;
        }
        body = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
      } finally {
        response.close();
      }
      if (StringUtils.isEmpty(body)) {
        logger.error("Response of API-Call was empty: {}", url);
        return null;
      }
      final JSONParser jparser = new JSONParser();
      final JSONObject jobj = (JSONObject) jparser.parse(body);
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
