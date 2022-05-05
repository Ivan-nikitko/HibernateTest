package pl.nikitko.model.api;

import pl.nikitko.model.api.EngineId;

import javax.persistence.*;

@Entity
@Inheritance (strategy = InheritanceType.JOINED)
//@MappedSuperclass()
public abstract class Engine {

    @Column(name = "engine_model")
    protected String engineModel;

    protected Integer power;

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

    public void setEngineModel(String engineModel) {
        this.engineModel = engineModel;
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
