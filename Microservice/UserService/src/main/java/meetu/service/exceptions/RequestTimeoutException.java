package meetu.service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.REQUEST_TIMEOUT)
public class RequestTimeoutException extends RuntimeException {

    private String massage = "Your request is timeout";

    public RequestTimeoutException() {
        super();
    }

    public String getMassage() {
        return this.massage;
    }

    
    

    
}