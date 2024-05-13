package DTOs;

import Persistence.Model.Role;
import Persistence.Model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TokenDTO {
    String token;
    String username;
    private Set<String> roles;

    public TokenDTO(String token, User user) {
        this.token = token;
        this.username = user.getUsername();
        this.roles = user.getRolesAsStrings(); // Assuming you have a method to get role names as strings
    }
}
