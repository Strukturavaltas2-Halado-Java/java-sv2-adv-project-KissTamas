package marketplace.marketplace.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDto {

    private Long Id;
    private String name;
    private String password;
    private String email;
    private List<AdDto> adList;

    public UserDto(Long id, String name, String password, String email) {
        Id = id;
        this.name = name;
        this.password = password;
        this.email = email;
    }
}
