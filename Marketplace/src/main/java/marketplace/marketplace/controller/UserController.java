package marketplace.marketplace.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import marketplace.marketplace.dto.CreateUserCommand;
import marketplace.marketplace.dto.UpdateUserCommand;
import marketplace.marketplace.service.MarketplaceService;
import marketplace.marketplace.dto.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private MarketplaceService marketplaceService;

    @GetMapping
    @Operation(summary = "list all users")
    public List<UserDto> getUsers() {
        return marketplaceService.getUsers();
    }

    @GetMapping("/{id}")
    public UserDto getUser(@PathVariable("id") Long id) {
        return marketplaceService.getUser(id);
    }

    @PostMapping
    @Operation(summary = "create new user")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto createUser(@Valid @RequestBody CreateUserCommand command) {
        return marketplaceService.createUser(command);
    }

    @PutMapping("/{id}")
    @Operation(summary = "update user data")
    public UserDto updateUser(@PathVariable("id") Long id, @Valid @RequestBody UpdateUserCommand command) {
        return marketplaceService.updateUser(id, command);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete user by id")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable("id") Long id) {
        marketplaceService.deleteUser(id);
    }

}
