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
 * OpenWeather Model - OpenWeatherResponse.
 *
 * @author Dominic Schaa
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "coord", "weather", "wind", "id", "name", "country" })
public class OpenWeatherResponse {

  @JsonProperty("coord")
  private Coord coord;
  @JsonIgnore
  private Weather weather = null;
  @JsonIgnore
  private String base;
  @JacksonXmlProperty(localName = "weather")
  private Main main;
  @JsonIgnore
  private Integer visibility;
  @JsonProperty("wind")
  private Wind wind;
  @JsonIgnore
  private Rain rain;
  @JsonIgnore
  private Clouds clouds;
  @JsonIgnore
  private Integer dt;
  @JsonIgnore
  private Sys sys;
  @JsonIgnore
  private Integer timezone;
  @JsonProperty("id")
  private Integer id;
  @JsonProperty("name")
  private String name;
  @JsonIgnore
  private Integer cod;
  @JsonIgnore
  private Map<String, Object> additionalProperties = new HashMap<String, Object>();

  @JsonProperty("coord")
  public Coord getCoord() {
    return coord;
  }

  @JsonProperty("coord")
  public void setCoord(Coord coord) {
    this.coord = coord;
  }

  @JsonIgnore
  public Weather getWeather() {
    return weather;
  }

  @JsonProperty("weather")
  public void setWeather(Weather weather) {
    this.weather = weather;
  }

  @JsonIgnore
  public String getBase() {
    return base;
  }

  @JsonProperty("base")
  public void setBase(String base) {
    this.base = base;
  }

  @JacksonXmlProperty(localName = "weather")
  public Main getMain() {
    return main;
  }

  @JsonProperty("main")
  public void setMain(Main main) {
    this.main = main;
  }

  @JsonIgnore
  public Integer getVisibility() {
    return visibility;
  }

  @JsonProperty("visibility")
  public void setVisibility(Integer visibility) {
    this.visibility = visibility;
  }

  @JsonProperty("wind")
  public Wind getWind() {
    return wind;
  }

  @JsonProperty("wind")
  public void setWind(Wind wind) {
    this.wind = wind;
  }

  @JsonIgnore
  public Rain getRain() {
    return rain;
  }

  @JsonProperty("rain")
  public void setRain(Rain rain) {
    this.rain = rain;
  }

  @JsonIgnore
  public Clouds getClouds() {
    return clouds;
  }

  @JsonProperty("clouds")
  public void setClouds(Clouds clouds) {
    this.clouds = clouds;
  }

  @JsonIgnore
  public Integer getDt() {
    return dt;
  }

  @JsonProperty("dt")
  public void setDt(Integer dt) {
    this.dt = dt;
  }

  @JsonProperty("country")
  public Sys getSys() {
    return sys;
  }

  @JsonProperty("sys")
  public void setSys(Sys sys) {
    this.sys = sys;
  }

  @JsonIgnore
  public Integer getTimezone() {
    return timezone;
  }

  @JsonProperty("timezone")
  public void setTimezone(Integer timezone) {
    this.timezone = timezone;
  }

  @JsonProperty("id")
  public Integer getId() {
    return id;
  }

  @JsonProperty("id")
  public void setId(Integer id) {
    this.id = id;
  }

  @JsonProperty("name")
  public String getName() {
    return name;
  }

  @JsonProperty("name")
  public void setName(String name) {
    this.name = name;
  }

  @JsonIgnore
  public Integer getCod() {
    return cod;
  }

  @JsonProperty("cod")
  public void setCod(Integer cod) {
    this.cod = cod;
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
    return new ToStringBuilder(this).append("coord", coord).append("weather", weather)
        .append("base", base).append("main", main).append("visibility", visibility)
        .append("wind", wind).append("clouds", clouds).append("dt", dt).append("sys", sys)
        .append("timezone", timezone).append("id", id).append("name", name).append("cod", cod)
        .append("additionalProperties", additionalProperties).toString();
  }

}
