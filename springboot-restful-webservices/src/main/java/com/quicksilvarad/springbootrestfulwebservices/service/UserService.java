package com.quicksilvarad.springbootrestfulwebservices.service;
import com.quicksilvarad.springbootrestfulwebservices.entity.User;
import com.quicksilvarad.springbootrestfulwebservices.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import com.quicksilvarad.springbootrestfulwebservices.DTO.UserDTO;
import java.util.List;


public interface UserService {


    User createUser (User user);
    UserDTO createUserDTO (UserDTO user);
    User getUserById (Long userId);
    List<User> getAllUsers();
    User updateUser(User user);
    public UserDTO getUserDTOById(Long userId);


    public List<UserDTO> getAllUserDTOs();


    public UserDTO updateUserDTO(UserDTO user) ;
    void deleteUser(Long userId);
}
