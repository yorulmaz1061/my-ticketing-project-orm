package com.cydeo.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;

@Component
public class MapperUtil {
    //We have 3rd party class model mapper
    private final ModelMapper modelMapper;


    public MapperUtil(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
    // <T> is like initializing like I'm gonna use T
//    public <T> T convertToEntity(Object objectToBeConverted, T convertedObject){
//        return modelMapper.map(objectToBeConverted,(Type) convertedObject.getClass());
//    }
//    public <T> T convertToDTO(Object objectToBeConverted, T convertedObject){
//        return modelMapper.map(objectToBeConverted,(Type) convertedObject.getClass());
//    }
    public <T> T convert (Object objectToBeConverted, T convertedObject){
        return modelMapper.map(objectToBeConverted,(Type) convertedObject.getClass());
    }



}
