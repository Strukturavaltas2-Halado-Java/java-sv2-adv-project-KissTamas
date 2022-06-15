package marketplace.marketplace;

import lombok.AllArgsConstructor;
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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AdDto createAd(CreateAdCommand command) {
        return marketplaceService.createAd(command);
    }

    @PutMapping
    public AdDto updateAd() {
        return marketplaceService.updateAd();
    }

    @DeleteMapping
    public void deleteAd(){
        marketplaceService.deleteAd();
    }
}
