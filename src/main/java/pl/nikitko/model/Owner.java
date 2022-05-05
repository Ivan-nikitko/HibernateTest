package pl.nikitko.model;

import pl.nikitko.model.api.WheelDrive;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Map;


@NamedEntityGraph(name = "user.carList", attributeNodes = @NamedAttributeNode("carList"))
@Table(name = "owners", schema = "application")
@Entity
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

/*

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "car",
            joinColumns = {@JoinColumn(name = "owner_id", referencedColumnName = "id")})
    @MapKeyColumn(name = "vin")
    @Column(name = "car_model")
    private Map<Integer, String> carsModelByVin;


    @MapKey(name = "vin")
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "owner")
    private Map<Integer, Car> carsByVin;


    @MapKeyTemporal(TemporalType.DATE)
    @MapKey(name = "createdOn")
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "owner")
    private Map<Date, Car> carsByDate;


    @MapKeyEnumerated(EnumType.STRING)
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "owner")
    @MapKeyColumn(name = "wheelDrive")
    private Map<WheelDrive, Car> carsByWD;



    @OneToMany(fetch = FetchType.EAGER,mappedBy = "owner")
    @MapKeyJoinColumns({@MapKeyJoinColumn(name = "brand_id1"),@MapKeyJoinColumn(name = "brand_id2")})
    private Map<Brand, Car> carsByBrand;

*/

    @OneToMany(mappedBy = "owner",cascade = CascadeType.REMOVE)
    private List<Car> carList;

    public List<Car> getCarList() {
        return carList;
    }
/*

    public Map<Date, Car> getCarsByDate() {
        return carsByDate;
    }

    public Map<Integer, String> getCarsModelByVin() {
        return carsModelByVin;
    }

    public Map<WheelDrive, Car> getCarsByWD() {
        return carsByWD;
    }

    public Map<Brand, Car> getCarsByBrand() {
        return carsByBrand;
    }

    public void setCarsByVin(Map<Integer, Car> cars) {
        this.carsByVin = cars;
    }

    public Map<Integer, Car> getCarsByVin() {
        return carsByVin;
    }

*/

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
                ", cars=" + carList +
                '}';
    }
}
