package pl.nikitko.model;

import javax.persistence.*;

@Table(name = "car_brand")
@Entity
public class Brand {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String name;
    @Column
    private String country;

    public Brand() {
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
