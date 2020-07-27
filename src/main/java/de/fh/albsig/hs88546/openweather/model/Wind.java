package de.fh.albsig.hs88546.openweather.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * OpenWeather Model - Wind.
 *
 * @author Dominic Schaa
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "speed", "deg" })
public class Wind {

  @JsonProperty("speed")
  private Double speed;
  @JsonProperty("deg")
  private Integer deg;
  @JsonIgnore
  private Map<String, Object> additionalProperties = new HashMap<String, Object>();

  /**
   * No args constructor for use in serialization.
   * 
   */
  public Wind() {
  }

  /**
   * constructor of wind.
   * 
   * @param deg   - degree of wind
   * @param speed - speed of wind
   */
  public Wind(Double speed, Integer deg) {
    super();
    this.speed = speed;
    this.deg = deg;
  }

  @JsonProperty("speed")
  public Double getSpeed() {
    return speed;
  }

  @JsonProperty("speed")
  public void setSpeed(Double speed) {
    this.speed = speed;
  }

  @JsonProperty("deg")
  public Integer getDeg() {
    return deg;
  }

  @JsonProperty("deg")
  public void setDeg(Integer deg) {
    this.deg = deg;
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
    return new ToStringBuilder(this).append("speed", speed).append("deg", deg)
        .append("additionalProperties", additionalProperties).toString();
  }

}
