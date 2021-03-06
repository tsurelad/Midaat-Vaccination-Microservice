package vaccination.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * User
 */
@Entity
@Data
@Table(name="users")
public class User {
    @Id
    String username;

    @Column
    @JsonIgnore
    String password;

    @Column
    boolean enabled;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    List<Authority> authorities;

    public User() {

    }

    public User(String username, String password, boolean enabled) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
    }
}
