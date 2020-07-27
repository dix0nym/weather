package de.fh.albsig.hs88546.openweather;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.fh.albsig.hs88546.openweather.model.OpenWeatherResponse;

public class OpenWeatherParser {

  /**
   * Tries to parse JSONObject to OpenWeatherResponse.
   *
   * @param json - json data as string
   * @return OpenWeatherResponse
   */
  public OpenWeatherResponse parseJson(String json)
      throws JsonMappingException, JsonProcessingException {
    ObjectMapper mapper = new ObjectMapper();
    mapper.enable(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS);
    OpenWeatherResponse response = mapper.readValue(json, OpenWeatherResponse.class);
    return response;
  }

}
