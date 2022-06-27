package marketplace.marketplace.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdateUserCommand {
    @Schema(description = "User name", example = "John Doe")
    @NotBlank(message = "Name can not be blank")
    private String name;
    @Schema(description = "User password", example = "abcdefgh")
    @NotBlank(message = "Password can not be blank")
    private String password;
    @Schema(description = "Valid user email address", example = "johndoe@gmail.com")
    @NotBlank(message = "Email can not be blank")
    private String email;
}
