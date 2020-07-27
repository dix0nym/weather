package de.fh.albsig.hs88546.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import de.fh.albsig.hs88546.Application;

/**
 * XmlFormatter.
 *
 * @author Dominic Schaa
 *
 */
@Component
public class XmlFormatter {

  private static Logger logger = LogManager.getLogger(Application.class);

  /**
   * returns xml string of java object.
   * 
   * @param response - java object
   * @return xml string of java object
   * @throws JsonProcessingException - caused by jackson
   */
  public String formatToXml(Object response) {
    try {
      JacksonXmlModule module = new JacksonXmlModule();
      XmlMapper xmlMapper = new XmlMapper(module);
      String xml = xmlMapper.writeValueAsString(response);
      return xml;
    } catch (JsonProcessingException e) {
      logger.error("failed to process object to xml: {}", e);
      return "<exception>failed to process object to xml</exception>";
    }
  }
}
