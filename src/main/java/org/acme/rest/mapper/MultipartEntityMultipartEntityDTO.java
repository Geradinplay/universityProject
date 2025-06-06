package org.acme.rest.mapper;

import org.acme.model.MultipartEntity;
import org.acme.rest.client.MultipartEntityClient;
import org.acme.rest.userDTO.MultipartEntityDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "jakarta")
public interface MultipartEntityMultipartEntityDTO {
    MultipartEntity toModel(MultipartEntityDTO multipartEntityDTO);

    MultipartEntityDTO toDTO(MultipartEntity multipartEntity);
}
