package meetu.service.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {

    private static final Logger logger = LoggerFactory.getLogger(BadRequestException.class);

    private static final String INCORRECT_PARAM = "Incorrect Parameter";

    public BadRequestException() {
        super();
        logger.warn("something error");

    }

    public BadRequestException(String message) {
        super(message);
        logger.error(message);
    }

    public String getINCORRECT_PARAM() {
        return this.INCORRECT_PARAM;
    }

}
