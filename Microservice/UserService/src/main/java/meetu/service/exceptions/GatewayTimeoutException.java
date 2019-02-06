package meetu.service.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.GATEWAY_TIMEOUT)
public class GatewayTimeoutException extends RuntimeException {

  private static final Logger logger = LoggerFactory.getLogger(GatewayTimeoutException.class);

  public GatewayTimeoutException () {
    super("504 Gateway Timeout");
    logger.error("504 Gateway Timeout");
  }

}