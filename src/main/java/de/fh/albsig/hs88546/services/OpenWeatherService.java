package de.fh.albsig.hs88546.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import de.fh.albsig.hs88546.openweather.OpenWeather;
import de.fh.albsig.hs88546.openweather.OpenWeatherException;
import de.fh.albsig.hs88546.openweather.OpenWeatherParser;
import de.fh.albsig.hs88546.openweather.model.OpenWeatherResponse;
import org.springframework.stereotype.Service;

/**
 * OpenWeatherSerivce.
 *
 * @author Dominic Schaa
 *
 */
@Service
public class OpenWeatherService {

  private OpenWeather openweather;
  private OpenWeatherParser parser;
  private XmlFormatter formatter;

  /**
   * Constructor of OpenWeatherService.
   */
  public OpenWeatherService() {
    this.openweather = new OpenWeather();
    this.parser = new OpenWeatherParser();
    this.formatter = new XmlFormatter();
  }

  /**
   * Constructor of OpenWeatherService.
   * 
   * @param apiKey - apikey for openweather api
   */
  public OpenWeatherService(String apiKey) {
    this.openweather = new OpenWeather(apiKey);
    this.parser = new OpenWeatherParser();
    this.formatter = new XmlFormatter();
  }

  /**
   * returns current weather by cityname as xml.
   * 
   * @param city - name of city
   * @return current weather as xml
   * @throws OpenWeatherException    - exception caused by OpenWeather
   * @throws JsonProcessingException - exception caused by OpenWeatherParser
   */
  public String getWeatherByCity(String city) throws OpenWeatherException, JsonProcessingException {
    String json = this.openweather.getByCityName(city);
    OpenWeatherResponse response = this.parser.parseJson(json);
    return this.formatter.formatToXml(response);
  }

  /**
   * returns current weather by cityid as xml.
   * 
   * @param id - id of city
   * @return current weather as xml
   * @throws OpenWeatherException    - exception caused by OpenWeather
   * @throws JsonProcessingException - exception caused by OpenWeatherParser
   */
  public String getWeatherById(int id) throws OpenWeatherException, JsonProcessingException {
    String json = this.openweather.getByCityId(id);
    OpenWeatherResponse response = this.parser.parseJson(json);
    return this.formatter.formatToXml(response);
  }

  /**
   * returns current weather by latitude and longitude as xml.
   * 
   * @param lat - latitude
   * @param lon - longitude
   * @return current weather as xml
   * @throws OpenWeatherException    - exception caused by OpenWeather
   * @throws JsonProcessingException - exception caused by OpenWeatherParser
   */
  public String getWeatherByCoords(double lat, double lon)
      throws OpenWeatherException, JsonProcessingException {
    String json = this.openweather.getByCoords(lat, lon);
    OpenWeatherResponse response = this.parser.parseJson(json);
    return this.formatter.formatToXml(response);
  }

  /**
   * returns current weather by zipcode and country code as xml.
   * 
   * @param code    - zipcode of city
   * @param country - country code
   * @return current weather as xml
   * @throws OpenWeatherException    - exception caused by OpenWeather
   * @throws JsonProcessingException - exception caused by OpenWeatherParser
   */
  public String getWeatherByZip(int code, String country)
      throws OpenWeatherException, JsonProcessingException {
    String json = this.openweather.getByZip(code, country);
    OpenWeatherResponse response = this.parser.parseJson(json);
    return this.formatter.formatToXml(response);
  }
}
