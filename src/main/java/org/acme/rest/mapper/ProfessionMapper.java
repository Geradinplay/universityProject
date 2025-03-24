package org.acme.rest.mapper;

import org.acme.model.Profession;

import org.acme.rest.userDTO.ProfessionDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "jakarta")
public interface ProfessionMapper {
    Profession toModel(ProfessionDTO professionDTO);
    ProfessionDTO toDTO(Profession profession);
}




