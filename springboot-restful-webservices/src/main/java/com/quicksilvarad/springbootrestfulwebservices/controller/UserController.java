package com.quicksilvarad.springbootrestfulwebservices.controller;
import com.quicksilvarad.springbootrestfulwebservices.exception.ErrorDetails;
import com.quicksilvarad.springbootrestfulwebservices.exception.ResourceNotFoundException;
import com.quicksilvarad.springbootrestfulwebservices.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.quicksilvarad.springbootrestfulwebservices.entity.User;
import java.lang.*;
import java.time.LocalDateTime;
import java.util.List;
import com.quicksilvarad.springbootrestfulwebservices.DTO.UserDTO;
import org.springframework.web.context.request.WebRequest;
import com.quicksilvarad.springbootrestfulwebservices.exception.EmailIDAlreadyExistsException;



@Tag(
        name="CRUD REST APIs for User Resource",
        description="CRUD APIs- Create User, Update User, Get User, Get all Users, Delete User"
)
@RestController
@AllArgsConstructor
@RequestMapping("api/users")
public class UserController {
    private UserService userService; //UserService object

    //build create REST API with User DTO

    @Operation(
            summary="Create User REST API",
            description="Create User REST API is used to save user in database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP status 201"
    )
    @PostMapping
    public ResponseEntity<UserDTO> createUserDTO(@RequestBody @Valid UserDTO user){
        UserDTO savedUser = userService.createUserDTO(user);
        return new ResponseEntity<>(savedUser,HttpStatus.CREATED);

    }
    //build create REST API and returns JPA User object
   /*@PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
        User savedUser = userService.createUser(user);
        return new ResponseEntity<>(savedUser,HttpStatus.CREATED);

    }*/
    //build get REST API with JPA USER Object
   /* @GetMapping("{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long userId){
        User returnUser = userService.getUserById(userId);
        return new ResponseEntity<>(returnUser, HttpStatus.OK);
    }*/

    @Operation(
            summary="Get User REST API",
            description="Get User REST API is used to get saved user in database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP status 200"
    )
    //build get REST API with USER DTO
    @GetMapping("{id}")
    public ResponseEntity<UserDTO> getUserDTOById(@PathVariable("id") Long userId){
        UserDTO returnUser = userService.getUserDTOById(userId);
        return new ResponseEntity<>(returnUser, HttpStatus.OK);
    }


    //build getAllUsers API with JPA User Object

    /*@GetMapping
    public ResponseEntity<List<User>> getAllUsers()
    {
        List<User> allUsers = userService.getAllUsers();
        return new ResponseEntity<>(allUsers,HttpStatus.OK);
    }*/

    //build getAllUsers API with DTO
    @Operation(
            summary="Get all User REST API",
            description="Get User REST API is used to get all saved users in database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP status 200"
    )
    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers()
    {
        List<UserDTO> allUsers = userService.getAllUserDTOs();
        return new ResponseEntity<>(allUsers,HttpStatus.OK);
    }

    //Build updateUser REST API with JPA User object
    /*@PutMapping("{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") Long userId,@RequestBody User user)
    {
        user.setId(userId);
        User updatedUser = userService.updateUser(user);
        return new ResponseEntity<>(updatedUser,HttpStatus.OK);
    }*/

    @Operation(
            summary="Update User REST API",
            description="Update User REST API is used to update saved user in database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP status 200"
    )
    //Build updateUser REST API with DTO
    @PutMapping("{id}")
    public ResponseEntity<UserDTO> updateUserDTO(@PathVariable("id") Long userId,@RequestBody @Valid UserDTO user)
    {
        user.setId(userId);
        UserDTO updatedUser = userService.updateUserDTO(user);
        return new ResponseEntity<>(updatedUser,HttpStatus.OK);
    }

    @Operation(
            summary="Delete User REST API",
            description="Delete User REST API is used to delete saved user in database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP status 200"
    )

    //Create Delete User REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long userId)
    {

        userService.deleteUser(userId);
        return new ResponseEntity<>("User Deleted successfully",HttpStatus.GONE);
    }

    //To handle specific exceptions wrt to the controllers
 /*   @ExceptionHandler(ResourceNotFoundException.class) //specific class for handling exception
    public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFoundException exception, WebRequest webRequest){
        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), exception.getMessage() , webRequest.getDescription(false), "USER_NOT_FOUND" );
        System.out.println(exception.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

  */

}
