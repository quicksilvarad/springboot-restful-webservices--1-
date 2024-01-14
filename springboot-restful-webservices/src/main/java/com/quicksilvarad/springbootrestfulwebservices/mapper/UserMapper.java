package com.quicksilvarad.springbootrestfulwebservices.mapper;
import com.quicksilvarad.springbootrestfulwebservices.DTO.UserDTO;
import com.quicksilvarad.springbootrestfulwebservices.entity.User;

public class UserMapper {
public static UserDTO usertoDTO(User user){

    UserDTO userDTO = new UserDTO(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail());
    return userDTO;
}

    public static User dTOtoUser(UserDTO userDTO){

        User user = new User(69, userDTO.getFirstName(), userDTO.getLastName(), userDTO.getEmail());
        return user;
    }
}
