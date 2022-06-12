package marketplace.marketplace;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MarketplaceService {

    private UserRepository userRepository;

    private AdRepository adRepository;



}
