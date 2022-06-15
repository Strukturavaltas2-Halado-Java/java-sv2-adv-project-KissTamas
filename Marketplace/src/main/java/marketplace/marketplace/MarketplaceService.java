package marketplace.marketplace;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MarketplaceService {

    private UserRepository userRepository;
    private AdRepository adRepository;
    private ModelMapper modelMapper;

    public List<UserDto> getUsers() {
        List<User> result = userRepository.findAll();
        return result.stream().map(user -> modelMapper.map(user, UserDto.class)).toList();
    }

    public List<AdDto> getAds() {
        List<Ad> result = adRepository.findAll();
        return result.stream().map(ad -> modelMapper.map(ad, AdDto.class)).toList();
    }

    public AdDto createAd(CreateAdCommand command) {
        Ad newAd = new Ad(command.getCategory(), command.getPrice(), command.getPlace(), command.getDescription());
        adRepository.save(newAd);
        return modelMapper.map(newAd, AdDto.class);
    }

    public UserDto createUser(CreateUserCommand command) {
        User newUser = new User(command.getName(), command.getEmail(), command.getPassword());
        userRepository.save(newUser);
        return modelMapper.map(newUser, UserDto.class);
    }

    public UserDto updateUser() {
        return null;
    }

    public AdDto updateAd() {
        return null;
    }

    public void deleteAd() {

    }

    public void deleteUser() {

    }
}
