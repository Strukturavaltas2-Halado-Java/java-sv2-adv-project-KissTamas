package marketplace.marketplace;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Ad {

    private Long id;
    private Category category;
    private int price;
    private String place;
    private String description;

    public Ad(Category category, int price, String place, String description) {
        this.category = category;
        this.price = price;
        this.place = place;
        this.description = description;
    }
}
