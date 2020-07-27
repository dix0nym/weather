package de.fh.albsig.hs88546.openweather;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.Mockito;
import de.fh.albsig.hs88546.openweather.model.Clouds;
import de.fh.albsig.hs88546.openweather.model.Coord;
import de.fh.albsig.hs88546.openweather.model.Main;
import de.fh.albsig.hs88546.openweather.model.OpenWeatherResponse;
import de.fh.albsig.hs88546.openweather.model.Sys;
import de.fh.albsig.hs88546.openweather.model.Weather;
import de.fh.albsig.hs88546.openweather.model.Wind;
import de.fh.albsig.hs88546.services.XmlFormatter;

/**
 * Testcases for Class XmlFormatter.
 *
 * @author Dominic Schaa
 *
 */
@TestInstance(Lifecycle.PER_CLASS)
public class XmlFormatterTests {
  private OpenWeatherParser parser;
  private XmlFormatter formatter;
  private OpenWeatherResponse weather;
  private String result;
  private final File resultFile = new File("src/test/resources/weather.xml");

  @AfterAll
  public void clean() throws Exception {
  }

  @BeforeEach
  public void create() throws Exception {
    this.formatter = new XmlFormatter();
  }

  @AfterEach
  public void eachclean() throws Exception {
    this.parser = null;
    this.formatter = null;
  }

  /**
   * initialize test case.
   *
   */
  @BeforeAll
  public void init() throws Exception {
    this.parser = mock(OpenWeatherParser.class);
    this.weather = createWeather();
    when(this.parser.parseJson(Mockito.anyString())).thenReturn(this.weather);
    this.result = loadResult();
  }

  private String loadResult() throws FileNotFoundException, IOException {
    // read file to string and add to mock
    try (FileInputStream fis = new FileInputStream(resultFile)) {
      byte[] data = new byte[(int) resultFile.length()];
      fis.read(data);
      String str = new String(data, "UTF-8");
      return str;
    }
  }

  private OpenWeatherResponse createWeather() {
    OpenWeatherResponse weather = new OpenWeatherResponse();

    Coord coord = new Coord(9.03, 48.22);
    weather.setCoord(coord);

    Weather w = new Weather(801, "Clouds", "few clouds", "02d");
    weather.setWeather(w);

    Main main = new Main(27.99, 26.59, 26.11, 29.44, 1016, 31);
    weather.setMain(main);

    weather.setVisibility(10000);
    weather.setDt(1595260825);

    Wind wind = new Wind(1.79, 148);
    weather.setWind(wind);

    Clouds clouds = new Clouds(23);
    weather.setClouds(clouds);

    Sys sys = new Sys(3, 19376, "DE", 1595216686, 1595272522);
    weather.setSys(sys);

    weather.setTimezone(7200);
    weather.setId(2958595);
    weather.setName("Albstadt");
    weather.setCod(200);
    return weather;
  }

  @Test
  @DisplayName("testing XMLFormatter")
  public void testFormatterByCityId() throws Exception {
    OpenWeatherResponse response = this.parser.parseJson("test");
    String output = this.formatter.formatToXml(response);
    assertEquals(this.result, output);
  }
}
