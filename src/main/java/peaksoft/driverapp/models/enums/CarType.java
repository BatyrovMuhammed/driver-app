package peaksoft.driverapp.models.enums;

import lombok.Getter;

import java.math.BigDecimal;

/**
 * @author Beksultan
 */
@Getter
public enum CarType {

    STANDART(
            new BigDecimal(12),
            new BigDecimal(60)
    ),
    COMFORT(
            new BigDecimal(15),
            new BigDecimal(80)
    ),
    BUSINESS_CLASS(
            new BigDecimal(20),
            new BigDecimal(100)
    );

    private final BigDecimal pricePerKM;
    private final BigDecimal priceForLanding;

    CarType(BigDecimal pricePerKM,
            BigDecimal priceForLanding) {
        this.pricePerKM = pricePerKM;
        this.priceForLanding = priceForLanding;
    }

    public BigDecimal getAmount(Double distance) {
        BigDecimal bigDecimalDistance = BigDecimal.valueOf(distance);
        BigDecimal bigDecimal = pricePerKM.multiply(bigDecimalDistance);
        return bigDecimal.add(priceForLanding);
    }
}
