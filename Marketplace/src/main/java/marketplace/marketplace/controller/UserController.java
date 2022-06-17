package marketplace.marketplace.controller;

import lombok.AllArgsConstructor;
import marketplace.marketplace.dto.CreateUserCommand;
import marketplace.marketplace.dto.UpdateUserCommand;
import marketplace.marketplace.service.MarketplaceService;
import marketplace.marketplace.dto.UserDto;
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

    @GetMapping("/{id}")
    public UserDto getUser(@PathVariable("id") Long id) {
        return marketplaceService.getUser(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto createUser(@RequestBody CreateUserCommand command) {
        return marketplaceService.createUser(command);
    }

    @PutMapping("/{id}")
    public UserDto updateUser(@PathVariable("id") Long id, @RequestBody UpdateUserCommand command) {
        return marketplaceService.updateUser(id, command);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable("id") Long id) {
        marketplaceService.deleteUser(id);
    }

}
