package dtos;

import lombok.*;
import persistence.model.User;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemDTO {
    private int id;
    private String title;
    private String description;
    private double price;
    private String fullName;
    private String address;
    private int phoneNr;
    private int postalCode;
    private boolean status;
    private User user;
}