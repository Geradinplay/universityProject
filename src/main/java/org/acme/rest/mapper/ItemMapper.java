package org.acme.rest.mapper;

import org.acme.model.Item;
import org.acme.rest.userDTO.ItemDTO;
import org.mapstruct.Mapper;


@Mapper(componentModel = "jakarta")
public interface ItemMapper {
Item toModel(ItemDTO itemDTO);
ItemDTO toDTO(Item item);
}
