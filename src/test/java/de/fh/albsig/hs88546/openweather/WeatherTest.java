package de.fh.albsig.hs88546.openweather;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.FileInputStream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

/**
 * Testcases for Class Weather.
 *
 * @author Dominic Schaa
 *
 */
@TestInstance(Lifecycle.PER_CLASS)
public class WeatherTest {
  private Weather weather;
  private Weather testWeather;

  @BeforeEach
  public void create() throws Exception {
    this.testWeather = new Weather();
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
    when(this.weather.getDescription()).thenReturn("light rain");
    when(this.weather.getCountry()).thenReturn("DE");
    when(this.weather.getHumidity()).thenReturn(91);
    when(this.weather.getLat()).thenReturn(48.22);
    when(this.weather.getLon()).thenReturn(9.03);
    when(this.weather.getPressure()).thenReturn(1024);
    when(this.weather.getTemp()).thenReturn(8.55);
    when(this.weather.getTempMin()).thenReturn(7.78);
    when(this.weather.getTempMax()).thenReturn(8.89);
    when(this.weather.getWind_deg()).thenReturn(342);
    when(this.weather.getWind_speed()).thenReturn(2.24);
  }

  @Test
  public void testCityId() throws Exception {
    this.testWeather.setCityId(2958595);
    assertEquals(this.weather.getCityId(), this.testWeather.getCityId());
  }

  @Test
  public void testCity() throws Exception {
    this.testWeather.setCity("Albstadt");
    assertEquals(this.weather.getCity(), this.testWeather.getCity());
  }

  @Test
  public void testCoordinates() throws Exception {
    this.testWeather.setLat(48.22);
    this.testWeather.setLon(9.03);
    assertEquals(this.testWeather.getLat(), this.weather.getLat());
    assertEquals(this.testWeather.getLon(), this.weather.getLon());
  }

  @Test
  public void testDescription() throws Exception {
    this.testWeather.setDescription("light rain");
    assertEquals(this.weather.getDescription(), this.testWeather.getDescription());
  }

  @Test
  public void testCountry() throws Exception {
    this.testWeather.setCountry("DE");
    assertEquals(this.weather.getCountry(), this.testWeather.getCountry());
  }

  @Test
  public void testPressure() throws Exception {
    this.testWeather.setPressure(1024);
    assertEquals(this.weather.getPressure(), this.testWeather.getPressure());
  }

  @Test
  public void testTemp() throws Exception {
    this.testWeather.setTemp(8.55);
    this.testWeather.setTempMax(8.89);
    this.testWeather.setTempMin(7.78);
    assertEquals(this.weather.getTemp(), this.testWeather.getTemp());
    assertEquals(this.weather.getTempMax(), this.testWeather.getTempMax());
    assertEquals(this.weather.getTempMin(), this.testWeather.getTempMin());
  }

  @Test
  public void testWind() throws Exception {
    this.testWeather.setWind_deg(342);
    this.testWeather.setWind_speed(2.24);
    assertEquals(this.weather.getWind_deg(), this.testWeather.getWind_deg());
    assertEquals(this.weather.getWind_speed(), this.testWeather.getWind_speed());
  }

  @Test
  public void testToXml() throws Exception {
    File testFile = new File("src/test/resources/weather.xml");
    // read file to string and add to mock
    try (FileInputStream fis = new FileInputStream(testFile)) {
      byte[] data = new byte[(int) testFile.length()];
      fis.read(data);
      String str = new String(data, "UTF-8");
      when(this.weather.toXml()).thenReturn(str);
    }
    assertEquals(this.testWeather.toXml(), this.testWeather.toXml());

  }

  @AfterEach
  public void eachclean() throws Exception {
    this.testWeather = null;
  }

  @AfterAll
  public void clean() throws Exception {
    this.testWeather = null;
  }
}
