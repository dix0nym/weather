package de.fh.albsig.hs88546.openweather;

import java.io.StringWriter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Weather {
  private static Logger logger = LogManager.getLogger(Weather.class);

  private String city;
  private String description;
  private String fullDescription;
  private long humidity;
  private double lat;
  private double lon;
  private long pressure;

  private double temp;
  private double tempMax;
  private double tempMin;

  private long windDeg;
  private double windSpeed;

  public Weather() {
  }

  /**
   * Weather Constructor.
   *
   * @param description     description of weather
   * @param fullDescription full description of weather
   * @param temp            current temperature
   * @param pressure        current pressure
   * @param humidity        current humidity
   * @param tempMin         current minimal temperature
   * @param tempMax         current maximal temperature
   * @param city            name of city
   * @param windSpeed       current speed of wind
   * @param windDeg         current degree of wind
   * @param lon             longitude of city
   * @param lat             latitude of city
   */
  public Weather(String description, String fullDescription, double temp, long pressure,
      int humidity, double tempMin, double tempMax, String city, double windSpeed, int windDeg,
      double lon, double lat) {
    this.description = description;
    this.fullDescription = fullDescription;
    this.temp = temp;
    this.pressure = pressure;
    this.humidity = humidity;
    this.tempMin = tempMin;
    this.tempMax = tempMax;
    this.city = city;
    this.windSpeed = windSpeed;
    this.windDeg = windDeg;
    this.lon = lon;
    this.lat = lat;
  }

  public String getCity() {
    return this.city;
  }

  public String getDescription() {
    return this.description;
  }

  public String getFullDescription() {
    return this.fullDescription;
  }

  public long getHumidity() {
    return this.humidity;
  }

  public double getLat() {
    return this.lat;
  }

  public double getLon() {
    return this.lon;
  }

  public long getPressure() {
    return this.pressure;
  }

  public double getTemp() {
    return this.temp;
  }

  public double getTempMax() {
    return this.tempMax;
  }

  public double getTempMin() {
    return this.tempMin;
  }

  public long getWind_deg() {
    return this.windDeg;
  }

  public double getWind_speed() {
    return this.windSpeed;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setFullDescription(String fullDescription) {
    this.fullDescription = fullDescription;
  }

  public void setHumidity(long humidity) {
    this.humidity = humidity;
  }

  public void setLat(double lat) {
    this.lat = lat;
  }

  public void setLon(double lon) {
    this.lon = lon;
  }

  public void setPressure(long pressure) {
    this.pressure = pressure;
  }

  public void setTemp(double temp) {
    this.temp = temp;
  }

  public void setTempMax(double tempMax) {
    this.tempMax = tempMax;
  }

  public void setTempMin(double tempMin) {
    this.tempMin = tempMin;
  }

  public void setWind_deg(long windDeg) {
    this.windDeg = windDeg;
  }

  public void setWind_speed(double windSpeed) {
    this.windSpeed = windSpeed;
  }

  /**
   * Returns xml-string built from class attributes.
   *
   * @return xml-string
   */
  public String toXml() {
    try {
      final DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
      final DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
      final Document doc = docBuilder.newDocument();
      final Element rootElement = doc.createElement("weather");
      doc.appendChild(rootElement);

      final Element nameElement = doc.createElement("city");
      nameElement.appendChild(doc.createTextNode(this.getCity()));
      rootElement.appendChild(nameElement);

      final Element fullDescElement = doc.createElement("description");
      fullDescElement.appendChild(doc.createTextNode(this.getFullDescription()));
      rootElement.appendChild(fullDescElement);

      final Element tempElement = doc.createElement("temp");
      rootElement.appendChild(tempElement);

      final Element ctempElement = doc.createElement("avg");
      ctempElement.appendChild(doc.createTextNode(String.valueOf(this.getTemp())));
      tempElement.appendChild(ctempElement);

      final Element minTempElement = doc.createElement("min");
      minTempElement.appendChild(doc.createTextNode(String.valueOf(this.getTempMin())));
      tempElement.appendChild(minTempElement);

      final Element maxTempElement = doc.createElement("max");
      maxTempElement.appendChild(doc.createTextNode(String.valueOf(this.getTempMax())));
      tempElement.appendChild(maxTempElement);

      final Element windElement = doc.createElement("wind");
      rootElement.appendChild(windElement);

      final Element windspeedElement = doc.createElement("speed");
      windspeedElement.appendChild(doc.createTextNode(String.valueOf(this.getWind_speed())));
      windElement.appendChild(windspeedElement);

      final Element windDegElement = doc.createElement("degree");
      windDegElement.appendChild(doc.createTextNode(String.valueOf(this.getWind_deg())));
      windElement.appendChild(windDegElement);

      final Element pressureElement = doc.createElement("pressure");
      pressureElement.appendChild(doc.createTextNode(String.valueOf(this.getPressure())));
      rootElement.appendChild(pressureElement);

      final Element humidityElement = doc.createElement("humidity");
      humidityElement.appendChild(doc.createTextNode(String.valueOf(this.getHumidity())));
      rootElement.appendChild(humidityElement);
      final TransformerFactory transformerFactory = TransformerFactory.newInstance();
      final Transformer transformer = transformerFactory.newTransformer();
      final StringWriter writer = new StringWriter();
      transformer.transform(new DOMSource(doc), new StreamResult(writer));
      return writer.getBuffer().toString();
    } catch (final TransformerException e) {
      logger.error("failed to transform xml: {} {}", e.getMessage(), e);
    } catch (final Exception e) {
      logger.error("failed to generate XML from weather Obj: {} {}", e.getMessage(), e);
    }
    return null;
  }
}
