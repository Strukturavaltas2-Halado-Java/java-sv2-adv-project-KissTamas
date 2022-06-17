package marketplace.marketplace.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import marketplace.marketplace.model.Category;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AdDto {

    private Long id;
    private Category category;
    private int price;
    private String place;
    private String description;
}
