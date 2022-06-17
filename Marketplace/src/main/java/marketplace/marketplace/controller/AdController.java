package marketplace.marketplace.controller;

import lombok.AllArgsConstructor;
import marketplace.marketplace.dto.AdDto;
import marketplace.marketplace.dto.CreateAdCommand;
import marketplace.marketplace.dto.UpdateAdCommand;
import marketplace.marketplace.service.MarketplaceService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/ads")
public class AdController {

    private MarketplaceService marketplaceService;

    @GetMapping
    public List<AdDto> getAds() {
        return marketplaceService.getAds();
    }

    @GetMapping("/{id}")
    public AdDto getAd(@PathVariable("id") Long id) {
        return marketplaceService.getAd(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AdDto createAd(@RequestBody CreateAdCommand command) {
        return marketplaceService.createAd(command);
    }

    @PutMapping("/{id}")
    public AdDto updateAd(@PathVariable("id") Long id, @RequestBody UpdateAdCommand command) {
        return marketplaceService.updateAd(id, command);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAd(@PathVariable("id") Long id) {
        marketplaceService.deleteAd(id);
    }
}
