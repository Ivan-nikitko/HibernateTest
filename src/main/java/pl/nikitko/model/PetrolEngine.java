package pl.nikitko.model;

import pl.nikitko.model.api.Engine;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;


@Table(name = "petrol_engines", schema = "application")
@Entity
public class PetrolEngine extends Engine {

    @Column
    private Integer OctanFuelNumber;

    @OneToMany(mappedBy = "engine")
    private List<Car> cars = new java.util.ArrayList<>();

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public List<Car> getCars() {
        return cars;
    }


    public PetrolEngine() {
    }


    @Override
    public String toString() {
        return "DieselEngine{" +
                "power ='" + super.getPower() + '\'' +
                "engineModel='" + engineModel + '\'' +
                '}';
    }
}
