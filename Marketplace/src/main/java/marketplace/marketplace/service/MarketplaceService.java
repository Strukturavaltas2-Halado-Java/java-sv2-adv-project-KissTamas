package marketplace.marketplace.service;

import lombok.AllArgsConstructor;
import marketplace.marketplace.dto.*;
import marketplace.marketplace.exception.AdNotFoundException;
import marketplace.marketplace.exception.UserNotFoundException;
import marketplace.marketplace.model.Ad;
import marketplace.marketplace.model.User;
import marketplace.marketplace.repository.AdRepository;
import marketplace.marketplace.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
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
        User user = userRepository.findById(command.getUserId()).orElseThrow(() -> new UserNotFoundException(command.getUserId()));
        Ad newAd = new Ad(command.getCategory(), command.getPrice(), command.getPlace(), command.getDescription());
        user.addAd(newAd);
        adRepository.save(newAd);
        return modelMapper.map(newAd, AdDto.class);
    }

    public UserDto createUser(CreateUserCommand command) {
        User newUser = new User(command.getName(), command.getPassword(), command.getEmail());
        userRepository.save(newUser);
        return modelMapper.map(newUser, UserDto.class);
    }

    public UserDto updateUser(Long id, UpdateUserCommand command) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        user.setName(command.getName());
        user.setPassword(command.getPassword());
        user.setEmail(command.getEmail());
        return modelMapper.map(user, UserDto.class);
    }

    public AdDto updateAd(Long id, UpdateAdCommand command) {
        Ad ad = adRepository.findById(id).orElseThrow(() -> new AdNotFoundException(id));
        ad.setCategory(command.getCategory());
        ad.setPrice(command.getPrice());
        ad.setPlace(command.getPlace());
        ad.setDescription(command.getDescription());
        return modelMapper.map(ad, AdDto.class);
    }

    public void deleteAd(Long id) {
        Ad ad = adRepository.findById(id).orElseThrow(() -> new AdNotFoundException(id));
        adRepository.delete(ad);
    }

    public void deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        userRepository.delete(user);
    }

    public UserDto getUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        return modelMapper.map(user, UserDto.class);
    }

    public AdDto getAd(Long id) {
        Ad ad = adRepository.findById(id).orElseThrow(() -> new AdNotFoundException(id));
        return modelMapper.map(ad, AdDto.class);
    }
}
