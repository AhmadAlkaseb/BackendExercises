package dtos;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemDTO {

    private Integer id;
    private String title;
    private String description;
    private double price;
    private String fullName;
    private String address;
    private int phoneNr;

}
