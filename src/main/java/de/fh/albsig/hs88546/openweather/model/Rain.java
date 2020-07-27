package de.fh.albsig.hs88546.openweather.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * OpenWeather Model - Sys.
 *
 * @author Dominic Schaa
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Rain {

  @JsonIgnore
  private Map<String, Object> additionalProperties = new HashMap<String, Object>();

  /**
   * No args constructor for use in serialization.
   * 
   */
  public Rain() {
  }

  @JsonAnyGetter
  public Map<String, Object> getAdditionalProperties() {
    return this.additionalProperties;
  }

  @JsonAnySetter
  public void setAdditionalProperty(String name, Object value) {
    this.additionalProperties.put(name, value);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this).append("additionalProperties", additionalProperties)
        .toString();
  }

}
