package de.fh.albsig.hs88546.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import de.fh.albsig.hs88546.Application;
import de.fh.albsig.hs88546.openweather.OpenWeatherException;
import de.fh.albsig.hs88546.services.OpenWeatherService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

/**
 * WeatherController.
 *
 * @author Dominic Schaa
 *
 */
@RestController
public class WeatherController {

  @Autowired
  private final OpenWeatherService service;

  private static Logger logger = LogManager.getLogger(Application.class);

  public WeatherController(OpenWeatherService service) {
    this.service = service;
  }

  /**
   * returns current weather by city name.
   * 
   * @param city - name of city
   * @return current weather as string
   */
  @RequestMapping(value = "/weather/city/{city}", produces = { MediaType.APPLICATION_XML_VALUE })
  public String weatherCity(@PathVariable("city") String city) {
    try {
      String weather = this.service.getWeatherByCity(city);
      return weather;
    } catch (OpenWeatherException | JsonProcessingException e) {
      logger.error("failed to process requests (weatherByCityName): {}", e.getMessage());
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
    }
  }

  /**
   * returns current weather by city id (http://bulk.openweathermap.org/sample/).
   * 
   * @param id - id of city
   * @return current weather as string
   */
  @RequestMapping(value = "/weather/id/{id}", produces = { MediaType.APPLICATION_XML_VALUE })
  public String weatherCityId(@PathVariable("id") int id) {
    try {
      String weather = this.service.getWeatherById(id);
      return weather;
    } catch (OpenWeatherException | JsonProcessingException e) {
      logger.error("failed to process requests (weatherByCityId): {}", e.getMessage());
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
    }
  }

  /**
   * returns current weather by latitude and longitude of city.
   * 
   * @param lat - latitude
   * @param lon - longitude
   * @return current weather as string
   */
  @RequestMapping(value = "/weather/coords/{lat}/{lon}", produces = {
      MediaType.APPLICATION_XML_VALUE })
  public String weatherCoords(@PathVariable("lat") double lat, @PathVariable("lon") double lon) {
    try {
      String weather = this.service.getWeatherByCoords(lat, lon);
      return weather;
    } catch (OpenWeatherException | JsonProcessingException e) {
      logger.error("failed to process requests (weatherByCoord): {}", e.getMessage());
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
    }
  }

  /**
   * returns current weather by zipcode and country.
   * 
   * @param code    - zipcode of city
   * @param country - country code: de, us, etc.
   * @return current weather as string
   */
  @RequestMapping(value = "/weather/zip/{code}/{country}", produces = {
      MediaType.APPLICATION_XML_VALUE })
  public String weatherZip(@PathVariable("code") int code,
      @PathVariable("country") String country) {
    try {
      String weather = this.service.getWeatherByZip(code, country);
      return weather;
    } catch (OpenWeatherException | JsonProcessingException e) {
      logger.error("failed to process requests (weatherByZip): {}", e.getMessage());
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
    }
  }
}
