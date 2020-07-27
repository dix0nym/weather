package de.fh.albsig.hs88546.openweather;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.fh.albsig.hs88546.openweather.model.OpenWeatherResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class OpenWeatherParser {
  private static Logger logger = LogManager.getLogger(OpenWeatherParser.class);

  /**
   * Tries to parse JSONObject to OpenWeatherResponse.
   *
   * @param json - json data as string
   * @return OpenWeatherResponse
   */
  public OpenWeatherResponse parseJson(String json)
      throws JsonMappingException, JsonProcessingException {
    logger.debug("parsing {}", json);
    ObjectMapper mapper = new ObjectMapper();
    mapper.enable(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS);
    OpenWeatherResponse response = mapper.readValue(json, OpenWeatherResponse.class);
    logger.debug("parsing successfull: {}", response);
    return response;
  }

}
