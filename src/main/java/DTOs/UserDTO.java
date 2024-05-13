package DTOs;

import Persistence.Model.User;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDTO {
    private String username;
    private String password;
    private Set<String> roles;

    public UserDTO(User user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.roles = user.getRolesAsStrings();
    }

    public UserDTO(String username, Set<String> roleSet) {
        this.username = username;
        this.roles = roleSet;
    }
}
