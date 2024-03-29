package pl.nikitko.model;

import pl.nikitko.model.api.Engine;

import javax.persistence.*;
import java.util.List;


@Table(name = "diesel_engines", schema = "application")
@Entity
@DiscriminatorValue("Diesel")
public class DieselEngine extends Engine {



    @OneToMany(mappedBy = "engine")
    private List<Car> cars = new java.util.ArrayList<>();

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public List<Car> getCars() {
        return cars;
    }



    public DieselEngine() {
    }


    @Override
    public String toString() {
        return "DieselEngine{" +
                "power ='" + super.getPower() + '\'' +
                "engineModel='" + engineModel + '\'' +
                '}';
    }
}
