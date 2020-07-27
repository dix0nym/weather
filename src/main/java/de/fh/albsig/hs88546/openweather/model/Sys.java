package de.fh.albsig.hs88546.openweather.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;
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
@JsonPropertyOrder({ "country", })
public class Sys {

  @JsonIgnore
  private Integer type;
  @JsonIgnore
  private Integer id;
  @JacksonXmlText
  @JsonProperty("country")
  private String country;
  @JsonIgnore
  private Integer sunrise;
  @JsonIgnore
  private Integer sunset;
  @JsonIgnore
  private Map<String, Object> additionalProperties = new HashMap<String, Object>();

  /**
   * No args constructor for use in serialization.
   * 
   */
  public Sys() {
  }

  /**
   * constructor of sys.
   * 
   * @param country - country
   * @param sunrise - time of sunrise
   * @param sunset  - time of sunset
   * @param id      - id of system
   * @param type    - type of system
   */
  public Sys(Integer type, Integer id, String country, Integer sunrise, Integer sunset) {
    super();
    this.type = type;
    this.id = id;
    this.country = country;
    this.sunrise = sunrise;
    this.sunset = sunset;
  }

  @JsonIgnore
  public Integer getType() {
    return type;
  }

  @JsonProperty("type")
  public void setType(Integer type) {
    this.type = type;
  }

  @JsonIgnore
  public Integer getId() {
    return id;
  }

  @JsonProperty("id")
  public void setId(Integer id) {
    this.id = id;
  }

  @JsonProperty("country")
  public String getCountry() {
    return country;
  }

  @JsonProperty("country")
  public void setCountry(String country) {
    this.country = country;
  }

  @JsonIgnore
  public Integer getSunrise() {
    return sunrise;
  }

  @JsonProperty("sunrise")
  public void setSunrise(Integer sunrise) {
    this.sunrise = sunrise;
  }

  @JsonIgnore
  public Integer getSunset() {
    return sunset;
  }

  @JsonProperty("sunset")
  public void setSunset(Integer sunset) {
    this.sunset = sunset;
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
    return new ToStringBuilder(this).append("type", type).append("id", id)
        .append("country", country).append("sunrise", sunrise).append("sunset", sunset)
        .append("additionalProperties", additionalProperties).toString();
  }

}
