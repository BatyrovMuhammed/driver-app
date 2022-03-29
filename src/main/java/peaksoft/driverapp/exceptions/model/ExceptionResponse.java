package peaksoft.driverapp.exceptions.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

/**
 * @author Beksultan
 */
@Getter @Setter
public class ExceptionResponse {
    private HttpStatus status;
    private String message;
}
