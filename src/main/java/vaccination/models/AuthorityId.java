package vaccination.models;

import lombok.Data;

import java.io.Serializable;

/**
 * AuthorityId
 */
@Data
public class AuthorityId implements Serializable {
    String username;
    String authority;

    public AuthorityId(){

    }

    public AuthorityId(String username, String authority) {
        this.username = username;
        this.authority = authority;
    }

    public AuthorityId(User user, String authority) {
        this.username = user.getUsername();
        this.authority = authority;
    }
}