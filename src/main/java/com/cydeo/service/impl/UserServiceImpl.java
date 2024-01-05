package com.cydeo.service.impl;

import com.cydeo.dto.UserDTO;
import com.cydeo.entity.User;
import com.cydeo.mapper.UserMapper;
import com.cydeo.repository.UserRepository;
import com.cydeo.service.UserService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
   private final UserRepository userRepository;
   private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public List<UserDTO> listAllUsers() {

        List<User> userList = userRepository.findAll(Sort.by("firstName"));
        //SELECT * FROM USERS where isDeleted=false

        return userList.stream().map(userMapper::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public UserDTO findByUserName(String username)
    {
        //goal is bring from database and populate it on ui
        User user=userRepository.findByUserName(username);
        return userMapper.convertToDTO(user);
    }

    @Override
    public void save(UserDTO dto) {
        userRepository.save(userMapper.convertToEntity(dto));




    }

    @Override
    public UserDTO update(UserDTO dto) {
        //Find current user.Capture the id.
        User user = userRepository.findByUserName(dto.getUserName());
        //Map updated user dto to entity object.
        User convertedUser = userMapper.convertToEntity(dto);
        //col 52 (user) has an id but col 54 (convertedUser) doesn't have an id.
        //set id to converted object..
        convertedUser.setId(user.getId());
        //save updated user
        userRepository.save(convertedUser);

    return findByUserName(dto.getUserName());
    }

    @Override
    public void deleteByUserName(String username) {
        userRepository.deleteByUserName(username);

    }

    @Override
    public void delete(String username) {
        //now I will not delete from dB I will just change the flag and keep it in the db.
        User user = userRepository.findByUserName(username);
        user.setIsDeleted(true);
        userRepository.save(user);

    }
}
