package marketplace.marketplace.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import marketplace.marketplace.dto.AdDto;
import marketplace.marketplace.dto.CreateAdCommand;
import marketplace.marketplace.dto.UpdateAdCommand;
import marketplace.marketplace.service.MarketplaceService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/api/ads")
public class AdController {

    private MarketplaceService marketplaceService;

    @GetMapping
    @Operation(summary = "list all advertises")
    public List<AdDto> getAds(@RequestParam Optional<String> cat,
                              @RequestParam Optional<Integer> minprice,
                              @RequestParam Optional<Integer> maxprice,
                              @RequestParam Optional<String> place) {
        return marketplaceService.getAds(cat, minprice, maxprice, place);
    }

    @GetMapping("/{id}")
    public AdDto getAd(@PathVariable("id") Long id) {
        return marketplaceService.getAd(id);
    }

    @PostMapping
    @Operation(summary = "create new advertise")
    @ResponseStatus(HttpStatus.CREATED)
    public AdDto createAd(@Valid @RequestBody CreateAdCommand command) {
        return marketplaceService.createAd(command);
    }

    @PutMapping("/{id}")
    @Operation(summary = "update advertise data")
    public AdDto updateAd(@PathVariable("id") Long id, @Valid @RequestBody UpdateAdCommand command) {
        return marketplaceService.updateAd(id, command);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete advertise by id")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAd(@PathVariable("id") Long id) {
        marketplaceService.deleteAd(id);
    }
}
