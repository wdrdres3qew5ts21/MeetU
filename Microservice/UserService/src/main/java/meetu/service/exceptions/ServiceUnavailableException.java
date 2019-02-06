package meetu.service.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.SERVICE_UNAVAILABLE)
public class ServiceUnavailableException extends RuntimeException {

  private static final Logger logger = LoggerFactory.getLogger(ServiceUnavailableException.class);

  public ServiceUnavailableException () {
    super("503 Service Unavailable");
    logger.error("503 Service Unavailable");
  }

}