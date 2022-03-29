package peaksoft.driverapp.models.entities;

import lombok.Getter;
import lombok.Setter;
import peaksoft.driverapp.models.entities.BankAccount;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static javax.persistence.CascadeType.*;
import static javax.persistence.FetchType.*;

@Entity
@Table(name = "clients")
@Getter
@Setter
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;

    private String phoneNumber;

    @OneToMany(cascade = {MERGE, DETACH, REFRESH})
    private List<Order> orders = new ArrayList<>();

    @OneToOne(cascade = ALL,
            fetch = EAGER)
    private BankAccount bankAccount;

    @OneToOne(cascade = ALL)
    private AuthInfo authInfo;
}