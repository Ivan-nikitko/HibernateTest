package pl.nikitko.model;

import pl.nikitko.model.api.BrandId;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;



@Table(name = "brand", schema = "application")
@Entity
@IdClass(BrandId.class)
public class Brand {

    @Id
    private Long firstIdPart;

    @Id
    private Long secondIdPart;

    @Column
    private String name;
    @Column
    private String country;



    @OneToMany(fetch = FetchType.EAGER, mappedBy = "brand")
    private List<Car> carList;

    public Brand() {
    }


    public void setFirstIdPart(Long firstIdPart) {
        this.firstIdPart = firstIdPart;
    }

    public void setSecondIdPart(Long secondIdPart) {
        this.secondIdPart = secondIdPart;
    }

    public List<Car> getCarList() {
        return carList;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Brand{" +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                '}';
    }

}
