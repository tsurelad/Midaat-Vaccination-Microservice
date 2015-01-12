package vaccination.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

/**
 * User Roles
 */
@Entity
@Data
@IdClass(AuthorityId.class)
@Table(name="authorities")
public class Authority {
    @ManyToOne
    @JoinColumn(name="username",insertable = false, updatable = false)
    @JsonIgnore
    User user;

    @Id
    String username;

    @Id
    String authority;

    public Authority() {

    }

    public Authority(User user, String authority) {
        this.username = user.getUsername();
        this.authority = authority;
    }

    public Authority(String username, String authority) {
        this.username = username;
        this.authority = authority;
    }

    @Override
    public String toString() {
        return "Authority{" +
                "username=" + username +
                ", authority='" + authority + '\'' +
                '}';
    }
}
