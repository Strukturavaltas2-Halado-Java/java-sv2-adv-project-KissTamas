package marketplace.marketplace;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CreateAdCommand {

    private Category category;
    private int price;
    private String place;
    private String description;
}
