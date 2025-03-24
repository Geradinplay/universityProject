package org.acme.rest.mapper;

import org.acme.model.User;
import org.acme.rest.userDTO.UserDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "jakarta", uses = {ItemMapper.class, ProfessionMapper.class})
public interface UserMapper {
    User toModel(UserDTO userDTO);
    UserDTO toDTO(User user);
}
