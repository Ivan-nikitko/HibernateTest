package pl.nikitko.model;

import pl.nikitko.model.api.Engine;
import pl.nikitko.model.api.WheelDrive;

import javax.persistence.*;
import java.util.Date;


@Table(name = "car", schema = "application")
@Entity
public class Car {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "car_model")
    private String carModel;

    @Column
    private int vin;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "brand_id1")
    @JoinColumn(name = "brand_id2")
    private Brand brand;


    @ManyToOne
    @JoinColumn(name = "engine_firstidpart")
    @JoinColumn(name = "engine_secondidpart")
    private Engine engine;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name = "owner_id")
    private Owner owner;

    @Enumerated(EnumType.STRING)
    private WheelDrive wheelDrive;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_on")
    private Date createdOn;

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public Owner getOwner() {
        return owner;
    }


    public void setWheelDrive(WheelDrive wheelDrive) {
        this.wheelDrive = wheelDrive;
    }

    public int getVin() {
        return vin;
    }

    public WheelDrive getWheelDrive() {
        return wheelDrive;
    }


    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Brand getBrand() {
        return brand;
    }

    public Car() {
    }

    public void setVin(int vin) {
        this.vin = vin;
    }


    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", carModel='" + carModel + '\'' +
                ", vin=" + vin +
                ", brand=" + brand +
                ", engine=" + engine +
              //  ", owner=" + owner +
                ", wheelDrive=" + wheelDrive +
                ", createdOn=" + createdOn +
                '}';
    }
}
