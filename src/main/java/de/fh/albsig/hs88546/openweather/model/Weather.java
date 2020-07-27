package de.fh.albsig.hs88546.openweather.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * OpenWeather Model - Weather.
 *
 * @author Dominic Schaa
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "id", "main", "description", })
public class Weather {

  @JsonProperty("id")
  private Integer id;
  @JsonIgnore
  private String main;
  @JsonIgnore
  private String description;
  @JsonIgnore
  private String icon;
  @JsonIgnore
  private Map<String, Object> additionalProperties = new HashMap<String, Object>();

  /**
   * No args constructor for use in serialization.
   * 
   */
  public Weather() {
  }

  /**
   * constructor of weather.
   * 
   * @param icon        - icon id of weather
   * @param description - description of weather
   * @param main        - short description of weather
   * @param id          - id of weather
   */
  public Weather(Integer id, String main, String description, String icon) {
    super();
    this.id = id;
    this.main = main;
    this.description = description;
    this.icon = icon;
  }

  @JsonProperty("id")
  public Integer getId() {
    return id;
  }

  @JsonProperty("id")
  public void setId(Integer id) {
    this.id = id;
  }

  @JacksonXmlProperty(localName = "description")
  public String getMain() {
    return main;
  }

  @JsonProperty("main")
  public void setMain(String main) {
    this.main = main;
  }

  @JsonIgnore
  public String getDescription() {
    return description;
  }

  @JsonProperty("description")
  public void setDescription(String description) {
    this.description = description;
  }

  @JsonIgnore
  public String getIcon() {
    return icon;
  }

  @JsonProperty("icon")
  public void setIcon(String icon) {
    this.icon = icon;
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
    return new ToStringBuilder(this).append("id", id).append("main", main)
        .append("description", description).append("icon", icon)
        .append("additionalProperties", additionalProperties).toString();
  }

}
