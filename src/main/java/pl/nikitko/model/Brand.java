package pl.nikitko.model;

import javax.persistence.*;
import java.util.List;

@NamedQueries({
        @NamedQuery(name = "brand.findAll", query = "from Brand B"),
        @NamedQuery(name = "brand.findByName",
                query = "from Brand b where b.name=:name"),
})


@Table(name = "brand", schema = "application")
@Entity
public class Brand {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String name;
    @Column
    private String country;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "brand")
    private List<Car> carList;

    public Brand() {
    }


    public List<Car> getCarList() {
        return carList;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
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
                "id=" + id +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
