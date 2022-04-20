package pl.nikitko.model.api;

import java.io.Serializable;

public class BrandId implements Serializable {

    protected Long firstIdPart;
    protected Long secondIdPart;

    public BrandId(Long firstIdPart, Long secondIdPart) {
        this.firstIdPart = firstIdPart;
        this.secondIdPart = secondIdPart;
    }

    public BrandId() {
    }

}