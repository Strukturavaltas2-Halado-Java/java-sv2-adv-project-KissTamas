package marketplace.marketplace.service;

import lombok.AllArgsConstructor;
import marketplace.marketplace.dto.*;
import marketplace.marketplace.exception.AdNotFoundException;
import marketplace.marketplace.exception.NotValidEmailAddressException;
import marketplace.marketplace.exception.UserNotFoundException;
import marketplace.marketplace.model.Ad;
import marketplace.marketplace.model.User;
import marketplace.marketplace.repository.AdRepository;
import marketplace.marketplace.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

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

    public List<AdDto> getAds(Optional<String> cat,
                              Optional<Integer> minprice,
                              Optional<Integer> maxprice,
                              Optional<String> place) {
        Type targetListType = new TypeToken<List<AdDto>>() {
        }.getType();
        List<Ad> filtered = adRepository.findAll().stream()
                .filter(e -> cat.isEmpty() || e.getCategory().toString().toLowerCase().equals(cat.get().toLowerCase()))
                .filter(e -> minprice.isEmpty() || e.getPrice() >= minprice.get())
                .filter(e -> maxprice.isEmpty() || e.getPrice() <= maxprice.get())
                .filter(e -> place.isEmpty() || e.getPlace().toLowerCase().equals(place.get().toLowerCase()))
                .toList();
        return modelMapper.map(filtered, targetListType);
    }

    public AdDto createAd(Long userId, CreateAdCommand command) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
        Ad newAd = new Ad(command.getCategory(), command.getPrice(), command.getPlace(), command.getDescription(), user);
        user.addAd(newAd);
        adRepository.save(newAd);
        return modelMapper.map(newAd, AdDto.class);
    }

    public UserDto createUser(CreateUserCommand command) {
        if (!isValidEmailAddress(command.getEmail())){
            throw new NotValidEmailAddressException(command.getEmail());
        }
        User newUser = new User(command.getName(), command.getPassword(), command.getEmail());
        userRepository.save(newUser);
        return modelMapper.map(newUser, UserDto.class);
    }

    public UserDto updateUser(Long id, UpdateUserCommand command) {
        if (!isValidEmailAddress(command.getEmail())){
            throw new NotValidEmailAddressException(command.getEmail());
        }
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

    private void deleteAdsByUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
        for (Ad a: user.getAdList()){
            adRepository.delete(a);
        }
    }

    public void deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        deleteAdsByUser(id);
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

    private boolean isValidEmailAddress(String emailAddress) {
        int atPos = Integer.MIN_VALUE;
        int dotPos = Integer.MIN_VALUE;
        for (int i = 0; i < emailAddress.length(); i++) {
            if (emailAddress.charAt(i) == '.') {
                dotPos = i;
            }
            if (emailAddress.charAt(i) == '@') {
                atPos = i;
            }
        }
        return (dotPos > atPos + 1 && atPos > 0);
    }

}
