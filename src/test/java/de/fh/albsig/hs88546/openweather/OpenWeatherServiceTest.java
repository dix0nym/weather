package de.fh.albsig.hs88546.openweather;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.Mockito;
import de.fh.albsig.hs88546.services.OpenWeatherService;
import de.fh.albsig.hs88546.services.XmlFormatter;

@TestInstance(Lifecycle.PER_CLASS)
public class OpenWeatherServiceTest {
  private final File resultFilte = new File("src/test/resources/weather.xml");
  private final File inputFile = new File("src/test/resources/weather.json");
  String result;
  String json;

  private OpenWeather openweather;

  OpenWeatherService service;

  /**
   * initialize test case.
   *
   */
  @BeforeAll
  public void init() throws Exception {
    this.result = loadFile(resultFilte);
    this.json = loadFile(inputFile);
  }

  @BeforeEach
  public void create() throws Exception {
    this.openweather = mock(OpenWeather.class);
    this.service = new OpenWeatherService(openweather, new OpenWeatherParser(), new XmlFormatter());
  }

  @AfterEach
  public void eachclean() throws Exception {
    this.service = null;
    this.openweather = null;
  }

  @Test
  @DisplayName("testing OpenWeatherService.getWeatherById")
  public void testGetByCityId() throws JsonProcessingException, OpenWeatherException {
    when(openweather.getByCityId(Mockito.anyInt())).thenReturn(json);
    String xml = service.getWeatherById(10);
    assertEquals(this.result, xml);
  }

  @Test
  @DisplayName("testing OpenWeatherService.getWeatherByCity")
  public void testGetByCityName() throws JsonProcessingException, OpenWeatherException {
    when(this.openweather.getByCityName(Mockito.anyString())).thenReturn(json);
    String xml = service.getWeatherByCity("any");
    assertEquals(this.result, xml);
  }

  @Test
  @DisplayName("testing OpenWeatherService.getWeatherByCoords")
  public void testGetByCoords() throws JsonProcessingException, OpenWeatherException {
    when(openweather.getByCoords(Mockito.anyDouble(), Mockito.anyDouble())).thenReturn(json);
    String xml = service.getWeatherByCoords(1.0, 1.0);
    assertEquals(this.result, xml);
  }

  @Test
  @DisplayName("testing OpenWeatherService.getWeatherByZip")
  public void testGetByZip() throws JsonProcessingException, OpenWeatherException {
    when(openweather.getByZip(Mockito.anyInt(), Mockito.anyString())).thenReturn(json);
    String xml = service.getWeatherByZip(1, "any");
    assertEquals(this.result, xml);
  }

  private String loadFile(File infile) throws FileNotFoundException, IOException {
    try (FileInputStream fis = new FileInputStream(infile)) {
      byte[] data = new byte[(int) infile.length()];
      fis.read(data);
      return new String(data, "UTF-8");
    }
  }

}
