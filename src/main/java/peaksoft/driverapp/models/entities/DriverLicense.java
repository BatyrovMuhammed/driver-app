package peaksoft.driverapp.models.entities;

import lombok.Getter;
import lombok.Setter;
import peaksoft.driverapp.models.enums.Category;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

import static javax.persistence.CascadeType.*;

/**
 * @author Beksultan
 */
@Entity
@Table(name = "driver_licenses")
@Getter @Setter
public class DriverLicense {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ElementCollection
    private Set<Category> categories;

    private LocalDate dateOfIssue;

    private LocalDate dateOfExpire;

    @OneToOne(cascade = {MERGE, DETACH, REFRESH})
    private Driver driver;
}
