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

  private double getDouble(JSONObject jobj, String key) {
    return ((Number) jobj.get(key)).doubleValue();
  }

  public Weather parseFile(File file) throws IOException, ParseException {
    FileInputStream fis = new FileInputStream(file);
    byte[] data = new byte[(int) file.length()];
    fis.read(data);
    fis.close();

    String str = new String(data, "UTF-8");
    JSONParser parser = new JSONParser();
    JSONObject json = (JSONObject) parser.parse(str);
    return this.parseJsonResponse(json);
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
}
