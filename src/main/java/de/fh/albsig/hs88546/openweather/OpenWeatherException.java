package de.fh.albsig.hs88546.openweather;

public class OpenWeatherException extends Exception {

  /**
   * OpenWeather Exception.
   *
   * @author Dominic Schaa
   *
   */
  private static final long serialVersionUID = 1738689581986066321L;

  public OpenWeatherException(String errorMessage, Throwable err) {
    super(errorMessage, err);
  }

  @Override
  public synchronized Throwable fillInStackTrace() {
    return null;
  }
}
