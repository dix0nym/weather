package de.fh.albsig.hs88546.openweather;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;
import static org.hamcrest.Matchers.containsString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import de.fh.albsig.hs88546.Application;
import de.fh.albsig.hs88546.controller.WeatherController;
import de.fh.albsig.hs88546.services.OpenWeatherService;
import de.fh.albsig.hs88546.services.XmlFormatter;

@WebMvcTest(WeatherController.class)
@ContextConfiguration(classes = { Application.class })
@TestInstance(Lifecycle.PER_CLASS)
public class SpingApplicationTest {

  private final File resultFile = new File("src/test/resources/weather.xml");
  String result;

  /**
   * initialize test case.
   *
   */
  @BeforeAll
  public void init() throws Exception {
    this.result = loadResult();
  }

  @AfterAll
  public void clean() throws Exception {
    this.result = null;
  }

  private String loadResult() throws FileNotFoundException, IOException {
    try (FileInputStream fis = new FileInputStream(resultFile)) {
      byte[] data = new byte[(int) resultFile.length()];
      fis.read(data);
      String str = new String(data, "UTF-8");
      return str;
    }
  }

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private OpenWeatherService service;

  @MockBean
  private XmlFormatter formatter;

  @Test
  public void testGetByCityName() throws Exception {
    this.result = loadResult();
    when(service.getWeatherByCity(Mockito.anyString())).thenReturn(result);
    this.mockMvc.perform(get("/weather/city/any")).andDo(print()).andExpect(status().isOk())
        .andExpect(content().string(containsString(this.result)));
  }

  @Test
  public void testGetByCityId() throws Exception {
    this.result = loadResult();
    when(service.getWeatherById(Mockito.anyInt())).thenReturn(result);
    this.mockMvc.perform(get("/weather/id/1")).andDo(print()).andExpect(status().isOk())
        .andExpect(content().string(containsString(this.result)));
  }

  @Test
  public void testGetByZip() throws Exception {
    this.result = loadResult();
    when(service.getWeatherByZip(Mockito.anyInt(), Mockito.anyString())).thenReturn(result);
    this.mockMvc.perform(get("/weather/zip/1/de")).andDo(print()).andExpect(status().isOk())
        .andExpect(content().string(containsString(this.result)));
  }

  @Test
  public void testgetByCoords() throws Exception {
    this.result = loadResult();
    when(service.getWeatherByCoords(Mockito.anyDouble(), Mockito.anyDouble())).thenReturn(result);
    this.mockMvc.perform(get("/weather/coords/1/1")).andDo(print()).andExpect(status().isOk())
        .andExpect(content().string(containsString(this.result)));
  }

}
