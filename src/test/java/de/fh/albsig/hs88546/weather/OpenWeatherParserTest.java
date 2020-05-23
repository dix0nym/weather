package de.fh.albsig.hs88546.weather;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.io.File;

import de.fh.albsig.hs88546.openweather.OpenWeatherParser;
import de.fh.albsig.hs88546.openweather.Weather;

@TestInstance(Lifecycle.PER_CLASS)
public class OpenWeatherParserTest {
  private final File testFile = new File("src/test/resources/weather.json");
  private Weather weather;
  private OpenWeatherParser parser;

  @AfterAll
  public void clean() throws Exception {
    this.weather = null;
  }

  @BeforeEach
  public void create() throws Exception {
    this.parser = new OpenWeatherParser();
  }

  @AfterEach
  public void fClean() throws Exception {
    this.parser = null;
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
  public void test() throws Exception {
    final Weather testWeather = this.parser.parseFile(this.testFile);
    assertEquals(this.weather.getCity(), testWeather.getCity());
    assertEquals(this.weather.getDescription(), testWeather.getDescription());
    assertEquals(this.weather.getFullDescription(), testWeather.getFullDescription());
    assertEquals(this.weather.getHumidity(), testWeather.getHumidity());
    assertEquals(this.weather.getLat(), testWeather.getLat());
    assertEquals(this.weather.getLon(), testWeather.getLon());
    assertEquals(this.weather.getPressure(), testWeather.getPressure());
    assertEquals(this.weather.getTemp(), testWeather.getTemp());
    assertEquals(this.weather.getTempMax(), testWeather.getTempMax());
    assertEquals(this.weather.getTempMin(), testWeather.getTempMin());
    assertEquals(this.weather.getWind_deg(), testWeather.getWind_deg());
    assertEquals(this.weather.getWind_speed(), testWeather.getWind_speed());
  }

}
