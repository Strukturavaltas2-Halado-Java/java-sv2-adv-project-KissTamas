package marketplace.marketplace.model;

import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "ads")
public class Ad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private Category category;
    private int price;
    private String place;
    private String description;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Ad(Category category, int price, String place, String description, User user) {
        this.category = category;
        this.price = price;
        this.place = place;
        this.description = description;
        this.user = user;
    }
}
