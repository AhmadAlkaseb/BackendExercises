package persistence.model;

import com.nimbusds.jose.shaded.json.annotate.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setteritems
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
    @Column(name = "full_name")
    private String fullName;
    private String address;
    @Column(name = "zip_code")
    private int zipCode;
    @Column(name = "phone_number")
    private int phoneNumber;
    @ColumnDefault("false")
    private boolean status;

    @JsonIgnore
    @ManyToOne()
    private User user;

    @ElementCollection
    @CollectionTable(name = "item_tags", joinColumns = @JoinColumn(name = "item_id"))
    @Column(name = "tag")
    private List<String> tags;

}
