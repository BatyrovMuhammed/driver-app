package peaksoft.driverapp.dto.car;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Beksultan
 */
@Builder
@Getter @Setter
public class CarSaveRequestResponse {
    private String driverName;
    private String carName;
}
