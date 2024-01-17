package com.cydeo.service.impl;

import com.cydeo.dto.RoleDTO;
import com.cydeo.entity.Role;
import com.cydeo.mapper.MapperUtil;
import com.cydeo.mapper.RoleMapper;
import com.cydeo.repository.RoleRepository;
import com.cydeo.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {
    //How I can call any method from roleRepository?
    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    private final MapperUtil mapperUtil;

    public RoleServiceImpl(RoleRepository roleRepository, RoleMapper roleMapper, MapperUtil mapperUtil) {
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
        this.mapperUtil = mapperUtil;
    }

    @Override
    public List<RoleDTO> listAllRoles() {
        //controller is calling me and requesting all roles
        //So I need to go to dataBase and bring all roles from there
        //List<Role> roleList= roleRepository.findAll();
        // we require a mechanism to convert entity to dto called MAPPERS
        //convert entity to dto-Mapper - get roles from db and convert each role to roledto
        // if you have a list how you can do sometihng in each item one by one?stream map method
        //roleList.stream().map(p->roleMapper.convertToDTO(p));
        // if your lambda expression directly call instance method you may use double colon op. as well
        //List<RoleDTO> list2= roleList.stream().map(roleMapper::convertToDTO).collect(Collectors.toList());
        //return list2;
     //   return roleRepository.findAll().stream().map(roleMapper::convertToDTO).collect(Collectors.toList());
        return roleRepository.findAll().stream().map(role->mapperUtil.convert(role, new RoleDTO())).collect(Collectors.toList());
    }

    @Override
    public RoleDTO findById(Long id) {
       // return roleMapper.convertToDTO(roleRepository.findById(id).get());
        return mapperUtil.convert(roleRepository.findById(id).get(), new RoleDTO());
    }
}
