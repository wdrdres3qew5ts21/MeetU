package meetu.service.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_GATEWAY)
public class BadGatewayException extends RuntimeException {

  private static final Logger logger = LoggerFactory.getLogger(BadGatewayException.class);

  public BadGatewayException () {
    super("502 Bad Gateway");
    logger.error("502 Bad Gateway");
  }

}