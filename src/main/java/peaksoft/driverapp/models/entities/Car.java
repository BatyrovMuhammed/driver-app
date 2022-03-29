package peaksoft.driverapp.models.entities;

import lombok.Getter;
import lombok.Setter;
import peaksoft.driverapp.models.enums.CarType;
import peaksoft.driverapp.models.enums.EngineType;

import javax.persistence.*;
import java.awt.*;
import java.util.UUID;

import static javax.persistence.CascadeType.*;

@Entity
@Table(name = "cars")
@Getter @Setter
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String brand;

    private String model;

    private String numberOfCar;

    private String color;

    private int yearOfIssue;

    @Enumerated(EnumType.STRING)
    private EngineType engineType;

    @OneToOne(cascade = {MERGE, REFRESH})
    private Driver carOwner;

    @Enumerated(EnumType.STRING)
    private CarType carType;

}