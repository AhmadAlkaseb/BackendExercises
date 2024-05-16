package persistence.Model;

import com.nimbusds.jose.shaded.json.annotate.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    private String title;
    private String description;
    private double price;

    private String fullName;
    private String address;
    private int postalCode;
    private int phoneNumber;
    @ColumnDefault("false")
    private boolean status;

    @JsonIgnore
    @ManyToOne()
    private User user;

    public Item(String title, String address) {
        this.title = title;
        this.address = address;
    }
}
