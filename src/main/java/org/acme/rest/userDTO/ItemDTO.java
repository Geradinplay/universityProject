package org.acme.rest.userDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.NotFound;

public class ItemDTO {
    @NotNull
    @JsonProperty("name")
    private String nameOfItem;

    public ItemDTO(String nameOfItem) {
        this.nameOfItem = nameOfItem;
    }

    public String getNameOfItem() {
        return nameOfItem;
    }

    public void setNameOfItem(String nameOfItem) {
        this.nameOfItem = nameOfItem;
    }
}
