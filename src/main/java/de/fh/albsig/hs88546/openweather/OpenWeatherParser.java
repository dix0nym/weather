package de.fh.albsig.hs88546.openweather;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class OpenWeatherParser {
  private static Logger logger = LogManager.getLogger(OpenWeatherParser.class);

  private Double getDouble(JSONObject jobj, String key) {
    if (!jobj.containsKey(key)) {
      return null;
    }
    return ((Number) jobj.get(key)).doubleValue();
  }

  private Integer getInt(JSONObject jobj, String key) {
    if (!jobj.containsKey(key)) {
      return null;
    }
    return ((Number) jobj.get(key)).intValue();
  }

  private String getString(JSONObject jobj, String key) {
    if (!jobj.containsKey(key)) {
      return null;
    }
    return jobj.get(key).toString();
  }

  /**
   * Tries to parse the supplied file using parseJsonResponse.
   *
   * @param file file to parse
   * @return parsed Weather-OBJ
   * @throws IOException    because of FileInputStream
   * @throws ParseException because of JSONParser
   */
  public Weather parseFile(File file) throws IOException, ParseException {
    try (FileInputStream fis = new FileInputStream(file)) {
      byte[] data = new byte[(int) file.length()];
      fis.read(data);
      String str = new String(data, "UTF-8");
      JSONParser parser = new JSONParser();
      JSONObject json = (JSONObject) parser.parse(str);
      return this.parseJsonResponse(json);
    } catch (Exception e) {
      logger.error("failed to parse file: {}", e.getMessage());
      return null;
    }
  }

  /**
   * Tries to parse JSONObject to Weather.
   *
   * @param jobj JObject to parse
   * @return Weather
   */
  public Weather parseJsonResponse(JSONObject jobj) {
    logger.info("parsing json response to weather obj");
    try {
      final Weather weather = new Weather();

      final String city = this.getString(jobj, "name");
      weather.setCity(city);

      weather.setCityId(this.getInt(jobj, "id"));

      final JSONObject coords = (JSONObject) jobj.get("coord");
      weather.setLon(this.getDouble(coords, "lon"));
      weather.setLat(this.getDouble(coords, "lat"));

      final JSONObject main = (JSONObject) jobj.get("main");
      weather.setTemp(this.getDouble(main, "temp"));
      weather.setTempMin(this.getDouble(main, "temp_min"));
      weather.setTempMax(this.getDouble(main, "temp_max"));
      weather.setPressure(this.getInt(main, "pressure"));
      weather.setHumidity(this.getInt(main, "humidity"));

      final JSONObject wind = (JSONObject) jobj.get("wind");
      weather.setWind_speed(this.getDouble(wind, "speed"));
      weather.setWind_deg(this.getInt(wind, "deg"));

      final JSONArray weatherArray = (JSONArray) jobj.get("weather");
      final JSONObject wObj = (JSONObject) weatherArray.get(0);
      weather.setDescription(this.getString(wObj, "description"));

      final JSONObject sysObj = (JSONObject) jobj.get("sys");
      weather.setCountry(this.getString(sysObj, "country"));

      return weather;
    } catch (final NullPointerException e) {
      logger.error("failed to parse weather obj from json: {}", e.getMessage());
    } catch (final Exception e) {
      logger.error("failed to parse: {}", e.getMessage());
    }
    return null;
  }
}
