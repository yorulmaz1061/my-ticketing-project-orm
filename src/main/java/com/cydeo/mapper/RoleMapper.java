package com.cydeo.mapper;

import com.cydeo.dto.RoleDTO;
import com.cydeo.entity.Role;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper {
    private final ModelMapper modelMapper;

    public RoleMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
    //2 methods I need. convertToEntity and convertToDto
    //Below is convertToEntity
    public Role convertToEntity(RoleDTO dto){
        return modelMapper.map(dto, Role.class);
    }
    //Below is convertToDTO
    public RoleDTO convertToDTO(Role entity){
        return modelMapper.map(entity,RoleDTO.class);

    }
}
