package dtos;

import lombok.*;

import persistence.Model.User;


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
    private User user; //har vi brug for en user også???

}