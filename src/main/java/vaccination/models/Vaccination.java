package vaccination.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.net.URI;
import java.util.List;

/**
 * Vaccination
 */
@Entity
@Data
public class Vaccination implements IdObject {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    Long id;

    String name;
    String description;
    String furtherInformation;
    URI externalLink;

    @OneToMany(mappedBy = "vaccination")
    @JsonIgnore
    List<VaccinationInCompound> compounds;

    @Override
    public String toString() {
        return "Vaccination{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", furtherInformation='" + furtherInformation + '\'' +
                ", externalLink=" + externalLink +
                '}';
    }
}
