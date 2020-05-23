package de.fh.albsig.hs88546.weather;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import de.fh.albsig.hs88546.openweather.Weather;

public class WeatherTest {
  private Weather weather;
  private Weather testWeather;

  @AfterAll
  public void clean() throws Exception {
    this.testWeather = null;
  }

  @BeforeEach
  public void create() throws Exception {
    this.weather = new Weather();
  }

  @AfterEach
  public void fClean() throws Exception {
    this.weather = null;
  }

  @BeforeAll
  public void init() throws Exception {
    this.weather = mock(Weather.class);
    when(this.weather.getCity()).thenReturn("Albstadt");
    when(this.weather.getDescription()).thenReturn("Rain");
    when(this.weather.getFullDescription()).thenReturn("light rain");
    when(this.weather.getHumidity()).thenReturn(91L);
    when(this.weather.getLat()).thenReturn(48.22);
    when(this.weather.getLon()).thenReturn(9.03);
    when(this.weather.getPressure()).thenReturn(1024L);
    when(this.weather.getTemp()).thenReturn(8.55);
    when(this.weather.getTempMin()).thenReturn(7.78);
    when(this.weather.getTempMax()).thenReturn(8.89);
    when(this.weather.getWind_deg()).thenReturn(342L);
    when(this.weather.getWind_speed()).thenReturn(2.24);
  }

  @Test
  public void testCity() throws Exception {
    this.weather.setCity("Albstadt");
    assertEquals(this.testWeather.getCity(), this.weather.getCity());
  }

  @Test
  public void testCoordniates() throws Exception {
    this.weather.setLat(48.22);
    this.weather.setLon(9.03);
    assertEquals(this.testWeather.getLat(), this.weather.getLat());
    assertEquals(this.testWeather.getLon(), this.weather.getLon());
  }

  @Test
  public void testDescription() throws Exception {
    this.weather.setDescription("Rain");
    this.weather.setFullDescription("light rain");
    assertEquals(this.testWeather.getDescription(), this.weather.getDescription());
    assertEquals(this.testWeather.getFullDescription(), this.weather.getFullDescription());
  }

  @Test
  public void testPressure() throws Exception {
    this.weather.setPressure(1024L);
    assertEquals(this.testWeather.getPressure(), this.weather.getPressure());
  }

  @Test
  public void testTemp() throws Exception {
    this.weather.setTemp(8.55);
    this.weather.setTempMax(8.89);
    this.weather.setTempMin(7.78);
    assertEquals(this.testWeather.getTemp(), this.weather.getTemp());
    assertEquals(this.testWeather.getTempMax(), this.weather.getTempMax());
    assertEquals(this.testWeather.getTempMin(), this.weather.getTempMin());
  }

  @Test
  public void testWind() throws Exception {
    this.weather.setWind_deg(342L);
    this.weather.setWind_speed(2.24);
    assertEquals(this.testWeather.getWind_deg(), this.weather.getWind_deg());
    assertEquals(this.testWeather.getWind_speed(), this.weather.getWind_speed());
  }
}
