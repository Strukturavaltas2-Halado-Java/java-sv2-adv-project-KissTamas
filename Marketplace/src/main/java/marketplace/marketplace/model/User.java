package marketplace.marketplace.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String name;
    private String password;
    private String email;
    @OneToMany(mappedBy = "user")
    private List<Ad> adList = new ArrayList<>();


    public User(String name, String password, String email) {
        this.name = name;
        this.password = password;
        this.email = email;
    }

    public void addAd(Ad ad) {
        adList.add(ad);
        ad.setUser(this);
    }

}
