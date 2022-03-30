package peaksoft.driverapp.dto.car;

import lombok.Getter;
import lombok.Setter;
import peaksoft.driverapp.models.enums.CarType;
import peaksoft.driverapp.models.enums.EngineType;

/**
 * @author Beksultan
 */
@Getter @Setter
public class CarSaveRequestDto {
    private String brand;
    private String model;
    private String numberOfCar;
    private String color;
    private int yearOfIssue;
    private EngineType engineType;
    private CarType carType;
}
