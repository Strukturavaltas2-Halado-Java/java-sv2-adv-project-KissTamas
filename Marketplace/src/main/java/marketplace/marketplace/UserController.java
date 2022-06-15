package marketplace.marketplace;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private MarketplaceService marketplaceService;

    @GetMapping
    public List<UserDto> getUsers() {
        return marketplaceService.getUsers();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto createUser(@RequestBody CreateUserCommand command) {
        return marketplaceService.createUser(command);
    }

    @PutMapping
    public UserDto updateUser() {
        return marketplaceService.updateUser();
    }

    @DeleteMapping
    public void deleteUser(){
        marketplaceService.deleteUser();
    }

}
