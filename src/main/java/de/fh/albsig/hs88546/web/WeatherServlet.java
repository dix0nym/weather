package de.fh.albsig.hs88546.web;

import de.fh.albsig.hs88546.openweather.OpenWeather;
import de.fh.albsig.hs88546.openweather.Weather;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * WeatherServlet Service.
 *
 * @author Dominic Schaa
 *
 */
public class WeatherServlet extends HttpServlet {
  private static Logger logger = LogManager.getLogger(WeatherServlet.class);

  private static final long serialVersionUID = 1L;

  public WeatherServlet() {
    super();
  }

  private void writeHtml(HttpServletResponse resp, PrintWriter writer, String title, String msg) {
    resp.setContentType("text/html");
    writer.println("<h1>" + title + "</h1>");
    writer.println("<p>" + msg + "</p>");
    writer.flush();
    writer.close();
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    resp.setContentType("application/xml;charset=UTF-8");
    final String city = req.getParameter("city");
    final String cityId = req.getParameter("cityId");
    final String lat = req.getParameter("lat");
    final String lon = req.getParameter("lon");
    final String code = req.getParameter("code");
    final String country = req.getParameter("country");
    final PrintWriter writer = resp.getWriter();
    try {
      Weather weather = null;
      final OpenWeather ow = new OpenWeather();
      if (!StringUtils.isEmpty(city)) {
        logger.info("requested with parameter: {}", city);
        weather = ow.getByCityName(city);
      } else if (!StringUtils.isEmpty(cityId)) {
        weather = ow.getByCityId(Integer.parseInt(cityId));
        logger.info("requested with parameter: {}", cityId);
      } else if (!StringUtils.isEmpty(lat) && !StringUtils.isEmpty(lon)) {
        logger.info("requested with parameter: {}, {}", lat, lon);
        weather = ow.getByCoords(Integer.parseInt(lat), Integer.parseInt(lon));
      } else if (!StringUtils.isEmpty(code) && !StringUtils.isEmpty(country)) {
        logger.info("requested with parameter: {} {}", code, country);
        weather = ow.getByZip(Integer.parseInt(code), country);
      } else {
        this.writeHtml(resp, writer, "Error", "Missing paramter: city|cityId|lat&lon|code&country");
        logger.error("Missing paramter: city|cityId|lat&lon|code&country");
        return;
      }
      writer.println(weather.toXml());
      writer.flush();
      writer.close();
    } catch (final Exception e) {
      this.writeHtml(resp, writer, "Error", e.getMessage());
      logger.error("Error in WeatherServlet: {} {}", e.getMessage(), e.getStackTrace());
    }
  }

}
