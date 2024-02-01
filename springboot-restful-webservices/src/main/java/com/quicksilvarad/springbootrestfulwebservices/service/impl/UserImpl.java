package com.quicksilvarad.springbootrestfulwebservices.service.impl;
import java.lang.*;

import com.quicksilvarad.springbootrestfulwebservices.exception.EmailIDAlreadyExistsException;
import com.quicksilvarad.springbootrestfulwebservices.exception.ResourceNotFoundException;
import com.quicksilvarad.springbootrestfulwebservices.mapper.UserMapper;
import com.quicksilvarad.springbootrestfulwebservices.DTO.UserDTO;
import com.quicksilvarad.springbootrestfulwebservices.entity.User;
import com.quicksilvarad.springbootrestfulwebservices.repository.UserRepository;
import com.quicksilvarad.springbootrestfulwebservices.service.UserService;
import lombok.AllArgsConstructor;
import org.hibernate.NonUniqueObjectException;
import org.jetbrains.annotations.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.sql.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service // To mark the class as a service
@AllArgsConstructor // lombok annotation or creating parameterised constructor
public class UserImpl extends java.lang.Exception implements UserService{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;
    //@Autowired
    //private RestTemplate restTemplate;
    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }
    @Override
    public UserDTO createUserDTO(UserDTO userDTO) {
        //Convert USER DTO into JPA entity
        //User user = UserMapper.dTOtoUser(userDTO);
        Optional<User> optionalUser = userRepository.findByEmail(userDTO.getEmail());
        if(optionalUser.isPresent()){
            throw new EmailIDAlreadyExistsException("Email already exists");
        }
        User user = modelMapper.map(userDTO, User.class);

        User savedUser = userRepository.save(user);
        //converting JPA entity into USER DTO entity
        //UserDTO savedUserDTO =UserMapper.usertoDTO(savedUser);
        UserDTO savedUserDTO = modelMapper.map(savedUser, UserDTO.class);
        return savedUserDTO;
    }

    @Override
    public UserDTO getUserDTOById(Long userId) {
        User optionalUser = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User","id",userId));
        UserDTO userDTO = modelMapper.map(optionalUser,UserDTO.class);
        return userDTO;
    }

    @Override
    public List<UserDTO> getAllUserDTOs() {
        List<User> allUsers = userRepository.findAll();
        //List<UserDTO> allDTOs = allUsers.stream().map(user -> new UserDTO(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail())).collect(Collectors.toList());
        List<UserDTO> allDTOs = allUsers.stream().map(user -> modelMapper.map(user,UserDTO.class)).collect(Collectors.toList());
        return allDTOs ;
    }

    @Override
    public UserDTO updateUserDTO(UserDTO userDTO) {
        //User user = new User(userDTO.getId(),userDTO.getFirstName(),userDTO.getLastName(),userDTO.getEmail());
        User user = modelMapper.map(userDTO,User.class);
        User existingUser = userRepository.findById(userDTO.getId()).orElseThrow(() -> new ResourceNotFoundException("User","Id", user.getId()));
        User updatedUser = updateUser(user);

        //return new UserDTO(updatedUser.getId(),updatedUser.getFirstName(),updatedUser.getLastName(),updatedUser.getEmail());
         return modelMapper.map(updatedUser,UserDTO.class);
    }

    @Override
    public User getUserById(Long userId) {
        Optional <User> optionalUser = userRepository.findById(userId);
        return optionalUser.get();
    }

    @Override
    public List<User> getAllUsers()
    {
        List<User> userList = userRepository.findAll();
        return userList;
    }

    @Override
    public User updateUser(User user) {
        User existingUser = userRepository.findById(user.getId()).get();
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        User updatedUser = userRepository.save(existingUser);
        return updatedUser;

    }

    @Override
    public void deleteUser(Long userId)
    {
        User existingUser = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User","Id", userId));
        userRepository.deleteById(userId);
        System.out.println("User deleted successfully");

    }

}
