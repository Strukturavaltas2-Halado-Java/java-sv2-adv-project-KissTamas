package marketplace.marketplace;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CreateUserCommand {

    private String name;
    private String password;
    private String email;

}
