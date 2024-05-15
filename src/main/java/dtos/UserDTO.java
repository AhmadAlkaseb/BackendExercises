package dtos;

import persistence.Model.User;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDTO {
    private String email;
    private String password;
    private Set<String> roles;

    public UserDTO(User user) {
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.roles = user.getRolesAsStrings();
    }

    public UserDTO(String email, Set<String> roleSet) {
        this.email = email;
        this.roles = roleSet;
    }
}
