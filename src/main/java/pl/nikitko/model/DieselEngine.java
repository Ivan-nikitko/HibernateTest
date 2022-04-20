package pl.nikitko.model;

import pl.nikitko.model.api.Engine;

import javax.persistence.*;
import java.util.List;


@Table(name = "diesel_engines", schema = "application")
@Entity
public class DieselEngine extends Engine {

    @Column(name = "engine_model")
    private String engineModel;

    @OneToMany(mappedBy = "dieselEngine")
    private List<Car> cars = new java.util.ArrayList<>();

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public List<Car> getCars() {
        return cars;
    }


    public void setEngineModel(String engineModel) {
        this.engineModel = engineModel;
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
