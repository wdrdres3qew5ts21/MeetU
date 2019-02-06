package meetu.service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class UnauthorizesException extends RuntimeException {

    private static final String UNAUTHORIZED = "You not login yet.";


    public UnauthorizesException() {
    }

    
    public String getUNAUTHORIZED() {
        return this.UNAUTHORIZED;
    }

    
}