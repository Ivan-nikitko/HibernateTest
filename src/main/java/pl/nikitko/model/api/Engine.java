package pl.nikitko.model.api;

import pl.nikitko.model.api.EngineId;

import javax.persistence.*;

//@Entity
@MappedSuperclass()
//@Inheritance (strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Engine {

    private Integer power;

    @EmbeddedId
    protected EngineId id;

    public Engine() {
    }

    public EngineId getId() {
        return id;
    }

    public void setId(EngineId id) {
        this.id = id;
    }

    public Integer getPower() {
        return power;
    }

    public void setPower(Integer power) {
        this.power = power;
    }

    @Override
    public String toString() {
        return "Engine{" +
                "power=" + power +
                '}';
    }
}
