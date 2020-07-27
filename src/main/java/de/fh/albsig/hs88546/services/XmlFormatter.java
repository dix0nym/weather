package de.fh.albsig.hs88546.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.stereotype.Component;

/**
 * XmlFormatter.
 *
 * @author Dominic Schaa
 *
 */
@Component
public class XmlFormatter {

  /**
   * returns xml string of java object.
   * 
   * @param response - java object
   * @return xml string of java object
   * @throws JsonProcessingException - caused by jackson
   */
  public String formatToXml(Object response) throws JsonProcessingException {
    JacksonXmlModule module = new JacksonXmlModule();
    XmlMapper xmlMapper = new XmlMapper(module);
    String xml = xmlMapper.writeValueAsString(response);
    return xml;
  }
}
