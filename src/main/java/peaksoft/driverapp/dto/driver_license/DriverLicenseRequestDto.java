package peaksoft.driverapp.dto.driver_license;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import peaksoft.driverapp.models.enums.Category;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

/**
 * @author Beksultan
 */
@Getter @Setter
public class DriverLicenseRequestDto {

    @NotNull
    private Set<Category> categories;

    @Past
    private LocalDate dateOfIssue;

    @Future
    @DateTimeFormat(pattern = "dd-MM-yy")
    private LocalDate dateOfExpire;
}
