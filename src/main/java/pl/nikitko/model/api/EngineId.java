package pl.nikitko.model.api;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class EngineId implements Serializable {

    protected Long firstIdPart;
    protected Long secondIdPart;

    public EngineId(Long firstIdPart, Long secondIdPart) {
        this.firstIdPart = firstIdPart;
        this.secondIdPart = secondIdPart;
    }

    public EngineId() {

    }


}