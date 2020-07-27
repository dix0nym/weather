package de.fh.albsig.hs88546.openweather;

import static org.junit.jupiter.api.Assertions.assertTrue;
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
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.Mockito;

import de.fh.albsig.hs88546.openweather.model.OpenWeatherResponse;

/**
 * Testcases for Class OpenWeatherParser.
 *
 * @author Dominic Schaa
 *
 */
@TestInstance(Lifecycle.PER_CLASS)
public class OpenWeatherParserTest {
  private final File testFile = new File("src/test/resources/weather.json");
  private OpenWeather openweather;
  private OpenWeatherParser parser;

  @AfterAll
  public void clean() throws Exception {
    this.openweather = null;
  }

  @BeforeEach
  public void create() throws Exception {
    this.parser = new OpenWeatherParser();
  }

  @AfterEach
  public void eachclean() throws Exception {
    this.parser = null;
  }

  private String loadResult() throws FileNotFoundException, IOException {
    // read file to string and add to mock
    try (FileInputStream fis = new FileInputStream(testFile)) {
      byte[] data = new byte[(int) testFile.length()];
      fis.read(data);
      String str = new String(data, "UTF-8");
      return str;
    }
  }

  /**
   * initialize test case.
   *
   */
  @BeforeAll
  public void init() throws Exception {
    this.openweather = mock(OpenWeather.class);
    String json = loadResult();
    when(this.openweather.getByCityId(Mockito.anyInt())).thenReturn(json);
    when(this.openweather.getByCityName(Mockito.anyString())).thenReturn(json);
    when(this.openweather.getByCoords(Mockito.anyDouble(), Mockito.anyDouble())).thenReturn(json);
    when(this.openweather.getByZip(Mockito.anyInt(), Mockito.anyString())).thenReturn(json);
  }

  private boolean check(OpenWeatherResponse response) {
    boolean id = response.getId() == 2958595;
    boolean name = response.getName().equals("Albstadt");
    return id && name;
  }

  @Test
  public void testByCityId() throws Exception {
    String json = this.openweather.getByCityId(1);
    OpenWeatherResponse response = this.parser.parseJson(json);
    assertTrue(check(response));
  }

  @Test
  public void testByCityName() throws Exception {
    String json = this.openweather.getByCityName("test");
    OpenWeatherResponse response = this.parser.parseJson(json);
    assertTrue(check(response));
  }

  @Test
  public void testByCoords() throws Exception {
    String json = this.openweather.getByCoords(1, 1);
    OpenWeatherResponse response = this.parser.parseJson(json);
    assertTrue(check(response));
  }

  @Test
  public void testByZip() throws Exception {
    String json = this.openweather.getByZip(1, "test");
    OpenWeatherResponse response = this.parser.parseJson(json);
    assertTrue(check(response));
  }

}
