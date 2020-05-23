package de.fh.albsig.hs88546.openweather;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

/**
 * Testcases for Class OpenWeather.
 *
 * @author Dominic Schaa
 *
 */
@TestInstance(Lifecycle.PER_CLASS)
public class OpenWeatherTests {
  private Weather weather;
  private Weather testweather;
  private OpenWeather openweather;

  @BeforeEach
  public void create() throws Exception {
    this.openweather = new OpenWeather();
  }

  @AfterEach
  public void eachclean() throws Exception {
    this.openweather = null;
  }

  /**
   * initialize test case.
   *
   */
  @BeforeAll
  public void init() throws Exception {
    this.weather = mock(Weather.class);
    when(this.weather.getCityId()).thenReturn(2958595);
    when(this.weather.getCity()).thenReturn("Albstadt");
    when(this.weather.getCountry()).thenReturn("DE");
    when(this.weather.getLat()).thenReturn(48.22);
    when(this.weather.getLon()).thenReturn(9.03);
  }

  private void compare() {
    assertEquals(this.weather.getCityId(), this.testweather.getCityId());
    assertEquals(this.weather.getCity(), this.testweather.getCity());
    assertEquals(this.weather.getCountry(), this.testweather.getCountry());
    assertEquals(this.weather.getLat(), this.testweather.getLat());
    assertEquals(this.weather.getLon(), this.testweather.getLon());
  }

  @Test
  public void testGetByCityId() throws Exception {
    this.testweather = this.openweather.getByCityId(2958595);
    this.compare();
  }

  @Test
  public void testGetByCityName() throws Exception {
    this.testweather = this.openweather.getByCityName("Albstadt");
    this.compare();
  }

  @Test
  public void testGetByCoords() throws Exception {
    this.testweather = this.openweather.getByCoords(48.22, 9.03);
    this.compare();
  }

  @Test
  public void testGetByZip() throws Exception {
    this.testweather = this.openweather.getByZip(72458, "DE");
    assertEquals(this.weather.getCity(), this.testweather.getCity());
    assertEquals(this.weather.getCountry(), this.testweather.getCountry());
  }

  @AfterAll
  public void clean() throws Exception {
    this.weather = null;
  }
}
