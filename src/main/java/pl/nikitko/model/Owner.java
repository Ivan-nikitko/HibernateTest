package pl.nikitko.model;

import pl.nikitko.model.api.WheelDrive;

import javax.persistence.*;
import java.util.List;
import java.util.Map;


@Table(name = "owner", schema = "application")
@Entity
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @MapKey(name = "vin")
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "owner")
    private Map<Integer, Car> carsByVin;

    @ElementCollection
    @CollectionTable(name = "owner_car_mapping",
            joinColumns = {@JoinColumn(name = "owner_id", referencedColumnName = "id")})
    @MapKeyColumn(name = "vin")
    @Column (name = "car_model")
    private Map<Integer, String> carsModelByVin;



    @OneToMany(fetch = FetchType.EAGER, mappedBy = "owner")
    @MapKeyJoinColumn(name = "brand_id")
    private Map<Brand, Car> carMap;


    //@ElementCollection
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "owner")
    @MapKeyEnumerated(EnumType.STRING)
    @MapKeyColumn(name = "wheelDrive")
    private Map<WheelDrive, Car> carsByWD ;

    @OneToMany(mappedBy = "owner")
    private List<Car> carList;

    public Map<WheelDrive, Car> getCarsByWD() {
        return carsByWD;
    }



    public Map<Brand, Car> getCarMap() {
        return carMap;
    }

    public void setCarsByVin(Map<Integer, Car> cars) {
        this.carsByVin = cars;
    }

    public Map<Integer, Car> getCarsByVin() {
        return carsByVin;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String toString() {
        return "Owner{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", cars=" + carsByVin +
                '}';
    }
}
