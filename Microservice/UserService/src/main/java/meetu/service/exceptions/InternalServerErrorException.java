package meetu.service.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class InternalServerErrorException extends RuntimeException {

  private static final Logger logger = LoggerFactory.getLogger(InternalServerErrorException.class);

  public InternalServerErrorException () {
    super("500 Internal Server Error");
  }

}