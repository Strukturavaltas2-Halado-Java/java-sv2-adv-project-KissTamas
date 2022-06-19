package marketplace.marketplace.dto;

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
    @NotBlank(message = "Name can not be blank")
    private String name;
    @NotBlank(message = "Password can not be blank")
    private String password;
    @NotBlank(message = "Email can not be blank")
    private String email;
}
