package vaccination.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.net.URI;
import java.util.List;

/**
 * Compound
 */
@Entity
@Data
public class Compound implements IdObject {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    Long id;

    String name;
    String compoundMethod;
    String description;
    String furtherInformation;
    String color;
    URI externalLink;

    @OneToMany(mappedBy = "compound")
    @JsonIgnore
    List<VaccinationInCompound> vaccinations;

    @OneToMany(mappedBy = "compound")
    @JsonIgnore
    List<CompoundInVaccinationDate> vaccinationDates;

    @Override
    public String toString() {
        return "Compound{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", compoundMethod='" + compoundMethod + '\'' +
                ", description='" + description + '\'' +
                ", furtherInformation='" + furtherInformation + '\'' +
                ", color='" + color + '\'' +
                ", externalLink=" + externalLink +
                '}';
    }
}
