package peaksoft.driverapp.models.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static javax.persistence.CascadeType.*;
import static javax.persistence.FetchType.*;

/**
 * @author Beksultan
 */
@Entity
@Table(name = "drivers")
@Getter @Setter
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String driverName;

    private String email;

    private int experience;

    private int rating;

    @OneToOne(cascade = ALL,
            fetch = LAZY)
    private DriverLicense driverLicense;

    @OneToOne(cascade = ALL)
    private Car car;

    @OneToOne(cascade = ALL,
            fetch = LAZY)
    private BankAccount bankAccount;

    @OneToOne(cascade = {MERGE, REFRESH},
            orphanRemoval = true,
            fetch = LAZY)
    private AuthInfo authInfo;

    @OneToMany(mappedBy = "driver",
            cascade = {MERGE, REFRESH, DETACH},
            orphanRemoval = true)
    private List<Order> orders = new ArrayList<>();

}
