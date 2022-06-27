package marketplace.marketplace.dto;

import io.swagger.v3.oas.annotations.media.Schema;
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
public class UpdateAdCommand {
    @NotNull
    @Schema(description = "Advertise category", example = "OTHER")
    private Category category;
    @Schema(description = "Guide price ", example = "1000")
    @NotNull
    @PositiveOrZero
    private int price;
    @Schema(description = "Place of trade", example = "Budapest")
    @NotBlank(message = "Place can not be blank")
    private String place;
    @Schema(description = "Advertise description", example = "nice thing")
    @NotBlank(message = "Description can not be blank")
    private String description;
}
