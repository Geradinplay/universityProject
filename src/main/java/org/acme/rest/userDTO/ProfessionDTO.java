package org.acme.rest.userDTO;

import jakarta.validation.constraints.NotNull;

public class ProfessionDTO {
    @NotNull
    private String nameOfProfession;

    public String getNameOfProfession() {
        return nameOfProfession;
    }

    public void setNameOfProfession(String nameOfProfession) {
        this.nameOfProfession = nameOfProfession;
    }
}
