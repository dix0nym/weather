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
import org.springframework.stereotype.Component;

/**
 * OpenWeather API.
 *
 * @author Dominic Schaa
 *
 */
@Component
public class OpenWeather {
  private static Logger logger = LogManager.getLogger(OpenWeather.class);

  private String apiKey = "d380ea7a6cbc8de8181f9cdc96df5982";

  public OpenWeather() {
  }

  private URIBuilder getBaseUrl() {
    URIBuilder baseUrl = new URIBuilder().setScheme("https").setHost("api.openweathermap.org")
        .setPath("/data/2.5/weather");
    return baseUrl;
  }

  /**
   * Overloaded OpenWeather Constructor to pass a API key.
   *
   * @param apiKey API-Key to use in requests
   */
  public OpenWeather(String apiKey) {
    logger.info("setting apiKey to {}", apiKey);
    this.apiKey = apiKey;
  }

  /**
   * Returns Weather by cityId see http://bulk.openweathermap.org/sample/
   *
   * @param cityId id of City
   * @return Weather
   * @throws OpenWeatherException - if performRequest fails
   */
  public String getByCityId(int cityId) throws OpenWeatherException {
    logger.info("requested weather by cityId: {}", cityId);
    URIBuilder builder = this.getBaseUrl();
    builder.addParameter("id", String.valueOf(cityId)).addParameter("appid", this.apiKey)
        .addParameter("units", "metric");
    String url = builder.toString();
    return this.performRequest(url);
  }

  /**
   * Returns Weather by city name.
   *
   * @param city name of the city
   * @return Weather
   * @throws OpenWeatherException - if performRequest fails
   */
  public String getByCityName(String city) throws OpenWeatherException {
    logger.info("requested weather by name: {}", city);
    URIBuilder builder = this.getBaseUrl();
    builder.addParameter("q", city).addParameter("appid", this.apiKey).addParameter("units",
        "metric");
    String url = builder.toString();
    return this.performRequest(url);
  }

  /**
   * Returns Weather by Coordinates.
   *
   * @param lat Latitude
   * @param lon Longitude
   * @return Weather
   * @throws OpenWeatherException - if performRequest fails
   */
  public String getByCoords(double lat, double lon) throws OpenWeatherException {
    logger.info("requested weather by coordinates: {}|{}", lat, lon);
    URIBuilder builder = this.getBaseUrl();
    builder.addParameter("lat", String.valueOf(lat)).addParameter("lon", String.valueOf(lon))
        .addParameter("appid", this.apiKey).addParameter("units", "metric");
    String url = builder.toString();
    return this.performRequest(url);
  }

  /**
   * Returns Weather by ZIP-Code and Country.
   *
   * @param code    ZIP-code
   * @param country ZIP in country
   * @return Weather
   * @throws OpenWeatherException - if performRequest fails
   */
  public String getByZip(int code, String country) throws OpenWeatherException {
    logger.info("requested weather by zip: {} in {}", code, country);
    URIBuilder builder = this.getBaseUrl();
    builder.addParameter("zip", String.format("%d,%s", code, country))
        .addParameter("appid", this.apiKey).addParameter("units", "metric");
    String url = builder.toString();
    return this.performRequest(url);
  }

  /**
   * Performs HTTP-Get request at URL and tries to parse response as JSON.
   *
   * @param url requested URL
   * @return JsonObject
   * @throws OpenWeatherException - if MalformedURL, IOException or
   *                              JsonParseExcepion is thrown
   */
  public String performRequest(String url) throws OpenWeatherException {
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
          throw new OpenWeatherException("failed to perform API-Request: " + statusCode, null);
        }
        body = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
      } finally {
        response.close();
      }
      if (StringUtils.isEmpty(body)) {
        logger.error("Response of API-Call was empty: {}", url);
        throw new OpenWeatherException("response of API-Call was empty for url: " + url, null);
      }
      return body;
    } catch (final MalformedURLException e) {
      logger.error("failed to generate valid url: {}", e.getMessage());
      throw new OpenWeatherException("request url was malformed", e);
    } catch (final IOException e) {
      logger.error("failed to perform API-Request: {}", e.getMessage());
      throw new OpenWeatherException("failed to perform API-Request", e);
    }
  }

}
