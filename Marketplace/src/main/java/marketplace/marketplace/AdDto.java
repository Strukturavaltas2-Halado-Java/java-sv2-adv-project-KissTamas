package marketplace.marketplace;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AdDto {

    private Long id;
    private Category category;
    private int price;
    private String place;
    private String description;
}
