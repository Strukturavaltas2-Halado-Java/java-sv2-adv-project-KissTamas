package marketplace.marketplace.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import marketplace.marketplace.model.Category;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateAdCommand {
    @NotNull
    private Category category;
    @PositiveOrZero
    @NotNull
    private int price;
    @NotBlank(message = "Place can not be blank")
    private String place;
    @NotBlank(message = "Description can not be blank")
    private String description;
}
