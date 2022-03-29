package peaksoft.driverapp.models.entities;

import lombok.Getter;
import lombok.Setter;
import peaksoft.driverapp.models.enums.CarType;
import peaksoft.driverapp.models.enums.Status;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;
import static javax.persistence.CascadeType.*;
import static peaksoft.driverapp.models.enums.CarType.*;

/**
 * @author Beksultan
 */
@Entity
@Table(name = "orders")
@Getter @Setter
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne(cascade = {MERGE, DETACH, REFRESH})
    private Client client;

    @ManyToOne(cascade = {PERSIST, MERGE, DETACH, REFRESH})
    private Address addressA;

    @ManyToOne(cascade = {PERSIST, MERGE, DETACH, REFRESH})
    private Address addressB;

    @ManyToOne(cascade = {MERGE, DETACH, REFRESH})
    private Driver driver;

    @Transient
    private BigDecimal price;

    private Double distance;

    @Enumerated(EnumType.STRING)
    private Status status;


    public BigDecimal getPrice() {
        CarType carType = driver.getCar().getCarType();
        return switch (carType) {
            case STANDART -> STANDART.getAmount(distance);
            case COMFORT -> COMFORT.getAmount(distance);
            case BUSINESS_CLASS -> BUSINESS_CLASS.getAmount(distance);
        };
    }
}
