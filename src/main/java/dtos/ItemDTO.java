package dtos;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemDTO {
    private Integer id;
    private String name;
    private String address;
   // private List<Room> rooms;
}