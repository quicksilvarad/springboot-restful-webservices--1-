package com.quicksilvarad.springbootrestfulwebservices.DTO;

///import lombok.AllArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(
   description="UserDTO model information"
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class UserDTO {


    private Long id;
    @NotEmpty(message = "should not be null or empty") //custom message for the error in validation using hibernate
    @Schema(description = "User firstName")
    private String firstName;
    @Schema(description = "User lastName")
    @NotEmpty (message = "should not be null or empty") //custom message for the error in validation using hibernate
    private String lastName;
    @Schema(description = "User Email ID")
    @NotEmpty(message = "should not be null or empty") @Email(message = "should be in valid format") //custom message for the error in validation using hibernate
    private String email;


}
