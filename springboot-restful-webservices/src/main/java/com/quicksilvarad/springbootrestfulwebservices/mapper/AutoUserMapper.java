package com.quicksilvarad.springbootrestfulwebservices.mapper;

import com.quicksilvarad.springbootrestfulwebservices.DTO.UserDTO;
import com.quicksilvarad.springbootrestfulwebservices.entity.User;
import org.mapstruct.Mapper;

@Mapper
public interface AutoUserMapper {
    UserDTO usertoUserDTO(User user);
    User userDTOtoUser(UserDTO userDTO);
}
