package marketplace.marketplace;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class UserDto {

    private Long Id;
    private String name;
    private String password;
    private String email;
    private List<Ad> adList;
}
