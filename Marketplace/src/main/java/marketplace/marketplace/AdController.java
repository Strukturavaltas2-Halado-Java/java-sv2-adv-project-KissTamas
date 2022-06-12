package marketplace.marketplace;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/ads")
public class AdController {

    private MarketplaceService marketplaceService;
}
