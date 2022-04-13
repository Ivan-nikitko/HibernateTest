package pl.nikitko.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;


@Table(name = "engine", schema = "application")
@Entity
public class Engine {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "engine_model")
    private String engineModel;

    @OneToMany(mappedBy = "engine")
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

    public Engine() {
    }

    @Override
    public String toString() {
        return "Engine{" +
                "engineModel='" + engineModel + '\'' +
                '}';
    }
}
