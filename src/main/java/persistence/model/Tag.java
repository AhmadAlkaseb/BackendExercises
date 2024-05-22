package persistence.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "tags")
public class Tag {

    @Id
    @Column(nullable = false)
    private String title;

    @ManyToMany(mappedBy = "tags")
    private Set<Item> item =  new HashSet<>();

    public Tag(String title) {
        this.title = title;
    }

}