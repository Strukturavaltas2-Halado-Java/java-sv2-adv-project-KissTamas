package marketplace.marketplace;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class User {

    private Long Id;
    private String name;
    private String password;
    private String email;
    private List<Ad> adList = new ArrayList<>();


    public User(String name, String password, String email) {
        this.name = name;
        this.password = password;
        this.email = email;
    }

}
